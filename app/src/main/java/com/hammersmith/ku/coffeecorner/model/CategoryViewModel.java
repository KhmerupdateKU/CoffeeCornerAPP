package com.hammersmith.ku.coffeecorner.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ku on 1/10/17.
 */

public class CategoryViewModel {

    @SerializedName("id")
    private String id;
    @SerializedName("image_url")
    private String imageView;
    @SerializedName("name")
    private String title;
    @SerializedName("price")
    private String price;
    @SerializedName("short_descriptions")
    private String desc;

    public CategoryViewModel(String desc, String price, String title, String imageView, String id) {
        this.desc = desc;
        this.price = price;
        this.title = title;
        this.imageView = imageView;
        this.id = id;
    }

    public CategoryViewModel(String id, String imageView, String price, String title) {
        this.id = id;
        this.imageView = imageView;
        this.price = price;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
