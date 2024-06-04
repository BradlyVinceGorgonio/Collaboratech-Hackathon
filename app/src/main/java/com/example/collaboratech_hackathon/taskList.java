package com.example.collaboratech_hackathon;

import android.widget.ImageView;

public class taskList {

    String title;
    String price;
    String description;
    String address;

    int image;

    public taskList(String title, String price, String description, String address, int image) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.address = address;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
