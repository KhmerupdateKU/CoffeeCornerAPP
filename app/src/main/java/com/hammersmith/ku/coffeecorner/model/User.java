package com.hammersmith.ku.coffeecorner.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ku on 12/28/16.
 */

public class User {

    @SerializedName("fbid")
    private String fbid;
    @SerializedName("email")
    private String email;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("name")
    private String name;

    public User(String fbid, String email, String first_name, String last_name, String name) {
        this.setFbid(fbid);
        this.setEmail(email);
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setName(name);
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}