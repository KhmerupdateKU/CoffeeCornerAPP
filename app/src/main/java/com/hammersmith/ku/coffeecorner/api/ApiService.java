package com.hammersmith.ku.coffeecorner.api;

import com.hammersmith.ku.coffeecorner.model.Category;
import com.hammersmith.ku.coffeecorner.model.CategoryViewModel;
import com.hammersmith.ku.coffeecorner.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ku on 1/18/17.
 */

public interface ApiService {
    @GET("main")
    Call<List<Category>> getCategories();

    @GET("categories/{id}")
    Call<List<CategoryViewModel>> getCategoriesModel(@Path("id") String id);

    @POST("user/register")
    Call<User> postUser();
}
