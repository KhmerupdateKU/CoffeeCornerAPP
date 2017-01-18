package com.hammersmith.ku.coffeecorner.model;

/**
 * Created by ku on 12/28/16.
 */

public class User {
    private int id;
    private String name;
    private String email;
    private String socialLink;
    private String socialType;
    private String photo;
    private String msg;
    private String password;
    private String address;
    private String phone;
    private String phone2;

    public User() {
    }

    public User(String socialLink) {
        this.socialLink = socialLink;
    }

    public User(String name, String email, String photo, String socialLink, String socialType) {
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.socialLink = socialLink;
        this.socialType = socialType;
    }

    public User(String name, String email, String password, String photo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialLink() {
        return socialLink;
    }

    public void setSocialLink(String socialLink) {
        this.socialLink = socialLink;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}
