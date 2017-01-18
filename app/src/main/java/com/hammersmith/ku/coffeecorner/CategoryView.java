package com.hammersmith.ku.coffeecorner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.hammersmith.ku.coffeecorner.adapter.AdapterCategoryView;
import com.hammersmith.ku.coffeecorner.api.ApiClient;
import com.hammersmith.ku.coffeecorner.api.ApiService;
import com.hammersmith.ku.coffeecorner.model.CategoryViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class CategoryView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AdapterCategoryView adapterCategoryView;
    private List<CategoryViewModel> categoryViewModels = new ArrayList<>();

    private String toolBarTitle,image_url,id;
    private ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCategoryShow);
        imageView = (ImageView) findViewById(R.id.imageCategory);




        toolBarTitle = getIntent().getStringExtra("title");
        image_url = getIntent().getStringExtra("image");
        id = getIntent().getStringExtra("id");

        getSupportActionBar().setTitle(toolBarTitle);

        Uri uri = Uri.parse(image_url);

        Context context = imageView.getContext();
        Picasso.with(context).load(uri).into(imageView);

        linearLayoutManager = new LinearLayoutManager(this, VERTICAL, false );
        recyclerView.setLayoutManager((linearLayoutManager));

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<List<CategoryViewModel>> call = service.getCategoriesModel(id);

        call.enqueue(new Callback<List<CategoryViewModel>>() {
            @Override
            public void onResponse(Call<List<CategoryViewModel>> call, Response<List<CategoryViewModel>> response) {
//                Log.d("Get Cate Model",response.body().toString());
                categoryViewModels = response.body();
                adapterCategoryView = new AdapterCategoryView(CategoryView.this, categoryViewModels);
                recyclerView.setAdapter(adapterCategoryView);
                adapterCategoryView.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CategoryViewModel>> call, Throwable t) {

            }
        });

    }
}
