package com.hammersmith.ku.coffeecorner.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ku on 1/10/17.
 */

public class Category {
    @SerializedName("id")
    private String id;
    @SerializedName("image_url")
    private String imageView;
    @SerializedName("title")
    private String title;
    @SerializedName("descriptions")
    private String shortDescription;

    public Category(String id, String imageView, String title, String shortDescription) {
        this.id = id;
        this.imageView = imageView;
        this.title = title;
        this.shortDescription = shortDescription;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
