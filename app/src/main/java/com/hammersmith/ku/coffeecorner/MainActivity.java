package com.hammersmith.ku.coffeecorner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.hammersmith.ku.coffeecorner.adapter.AdapterCategory;
import com.hammersmith.ku.coffeecorner.api.ApiClient;
import com.hammersmith.ku.coffeecorner.api.ApiService;
import com.hammersmith.ku.coffeecorner.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static com.hammersmith.ku.coffeecorner.SignInActivity.MY_PREF_NAME;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    // Category
    private ImageView imageView_profile;
    private TextView textView_profile, name_profile;

    private RecyclerView recyclerView9;
    private AdapterCategory adapterCategory;
    private LinearLayoutManager linearLayoutManager9;
    private List<Category> categories = new ArrayList<>();


    //google api client
    private int ID;
    private String Name;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //fb
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Category
        recyclerView9 = (RecyclerView) findViewById(R.id.recyclerViewCategory);


        // Category
        linearLayoutManager9 = new LinearLayoutManager(this, VERTICAL, false);
        recyclerView9.setLayoutManager((linearLayoutManager9));

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<List<Category>> call = service.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = response.body();
                Log.d("Get Main Title", categories.get(0).getId());
                adapterCategory = new AdapterCategory(MainActivity.this, categories);
                recyclerView9.setAdapter(adapterCategory);
                adapterCategory.notifyDataSetChanged();
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {


            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        imageView_profile = (ImageView) findViewById(R.id.imageView_Profile);
        textView_profile = (TextView) findViewById(R.id.textView_Profile);
        name_profile = (TextView) findViewById(R.id.name_account);






        ID = getIntent().getIntExtra("ID",0);
        buildGoogleApiClient(null);
//        Name = getIntent().getStringExtra("Name");
//        textView_profile.setText(Name);

    }

    private void buildGoogleApiClient(String name) {
        GoogleSignInOptions.Builder geoBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile();

        if (name != null) {
            geoBuilder.setAccountName(name);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient.stopAutoManage(MainActivity.this);
        }

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(MainActivity.this,0,this)
                .addApi(Auth.CREDENTIALS_API)
                .addApi(Auth.GOOGLE_SIGN_IN_API, geoBuilder.build());

        mGoogleApiClient = builder.build();

    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Log.d("Google Sign Out",status.getStatus().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //@Override
//    protected void onStart() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//        mGoogleApiClient.connect();
//        super.onStart();
    //}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.favorite_food) {


        } else if (id == R.id.nav_slideshow) {


        } else if (id == R.id.partnership) {
            Intent intent = new Intent(this, webView.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(MainActivity.this, MyProfile.class);
            startActivity(intent);


        } else if (id == R.id.nav_send) {
            if (ID == 1){
                signOut();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREF_NAME,MODE_PRIVATE).edit();
                editor.putBoolean("Google",false);
                editor.commit();
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

            if (ID == 2) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREF_NAME,MODE_PRIVATE).edit();
                editor.putBoolean("Google",false);
                editor.commit();
                FacebookSdk.sdkInitialize(getApplicationContext());
                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
