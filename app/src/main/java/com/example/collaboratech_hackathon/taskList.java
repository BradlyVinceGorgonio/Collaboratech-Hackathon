package com.example.collaboratech_hackathon;

import android.widget.ImageView;

public class taskList {

    String title;
    String price;
    String address;

    int image;

    String uid;

    public taskList(String title, String price, String address, int image, String uid) {
        this.title = title;
        this.price = price;
        this.address = address;
        this.image = image;
        this.uid = uid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}