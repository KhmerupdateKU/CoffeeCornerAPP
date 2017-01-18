package com.hammersmith.ku.coffeecorner.model;

/**
 * Created by ku on 1/16/17.
 */

public class Order {

    private int id;
    private int imageView;
    private String title;
    private String quantity;
    private  String price;


    public Order(int imageView, String title, String quantity, String price){
        this.imageView = imageView;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getImageView() {
        return imageView;
    }
    public String getPrice (){
        return price;
    }

}
