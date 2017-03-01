package com.hammersmith.ku.coffeecorner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.hammersmith.ku.coffeecorner.api.ApiClient;
import com.hammersmith.ku.coffeecorner.api.ApiService;
import com.hammersmith.ku.coffeecorner.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity  implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private User user;
    LoginButton loginButton;
    CallbackManager callbackManager;
    //Signin button
    private SignInButton signInButton;

    public static final String MY_PREF_NAME = "SignIn";

    private String emailGoogle,personId,name;
    Uri personPhoto;


    //google api client
    private GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME,MODE_PRIVATE);
        Boolean isSignin = prefs.getBoolean("Google",false);

        if (isSignin){
            Intent i = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplicationContext());
        callbackManager=CallbackManager.Factory.create();

        setContentView(R.layout.activity_sign_in);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile","user_friends","email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FaceBook Login","FB login Success");
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREF_NAME,MODE_PRIVATE).edit();
                editor.putBoolean("Facebook",true);
                editor.commit();
                requestData();
                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                i.putExtra("ID",2);
                startActivity(i);
                finish();

            }

            @Override
            public void onCancel() {
                Toast.makeText(SignInActivity.this, "Log in Cancel", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(SignInActivity.this, "Log in Error", Toast.LENGTH_LONG).show();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        //Initializing signinbutton
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Auth.CREDENTIALS_API)
                .build();


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);



    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("Google Login","Handle SignIn Result" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            if (account != null) {
                name = account.getDisplayName();
                emailGoogle = account.getEmail();
                personId = account.getId();
                Uri  personPhoto = account.getPhotoUrl();
                user = new User(personId,emailGoogle,"111","111",name);
                ApiService service = ApiClient.getClient().create(ApiService.class);
                Call<User> userRegister = service.postUser(user);
                userRegister.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()) {
                            try {
                                Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("LOG","Retrofit Response Error: "+t);
                    }
                });
               
            }
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("Name", name);
            startActivity(intent);
            finish();
            Toast.makeText(this,emailGoogle + " , " + name,Toast.LENGTH_LONG).show();
           intent.putExtra("ID",1);

        }
        else {
            Log.d("LoginActivity", "Error Log out Google");
        }
    }


    private void signIn() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREF_NAME,MODE_PRIVATE).edit();
        editor.putBoolean("Google",true);
        editor.commit();
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);

            int statusCode = result.getStatus().getStatusCode();
            Log.d("LoginActivity","Status Code"+statusCode);
            //fb
//            callbackManager.onActivityResult(requestCode, resultCode, data);

        }

        if (callbackManager != null){
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void requestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                JSONObject json = response.getJSONObject();
                if (json != null) {
                    try {
                        String fbUserName = json.getString("name");
                        String fbFirstName = json.getString("first_name");
                        String fbLastName = json.getString("last_name");
                        String fbEmail = json.getString("email");
                        String fbID = json.getString("id");
                        Toast.makeText(SignInActivity.this,"FACEBOOK :" + fbUserName + "," + fbEmail,Toast.LENGTH_LONG).show();
                        user = new User(fbID, fbEmail, fbFirstName, fbLastName, name);
                        ApiService service = ApiClient.getClient().create(ApiService.class);
                        Call<User> userRegister = service.postUser(user);
                        userRegister.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (!response.isSuccessful()) {
                                    try {
                                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.e("LOG","Retrofit Response Error: "+t);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Bundle params = new Bundle();
        params.putString("fields","id,name,email,link,picture");
        request.setParameters(params);
        request.executeAsync();
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {

            signIn();

        }
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}