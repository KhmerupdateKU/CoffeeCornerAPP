package com.hammersmith.ku.coffeecorner.model;

/**
 * Created by ku on 1/16/17.
 */

public class Order {

    private int id;
    private String imageView;
    private String title;
    private Integer quantity;
    private  Float price;


    public Order(int id, String imageView, Integer quantity, String title, Float price) {
        this.setId(id);
        this.setImageView(imageView);
        this.setQuantity(quantity);
        this.setTitle(title);
        this.setPrice(price);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
