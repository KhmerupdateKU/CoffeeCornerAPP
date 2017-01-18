package com.hammersmith.ku.coffeecorner.model;

/**
 * Created by ku on 1/4/17.
 */

public class Image {
    private int imageUrl;

    public Image(){

    }

    public Image(int imageUrl){
        this.imageUrl=imageUrl;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}

