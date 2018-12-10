package com.example.mattformicola.loger.Model;

import android.media.Image;

public class ImageUser {


    private String userName;
    private String email;
    private String zip;
    private Image imageURL;

    public ImageUser(String userName, String email, String email1, String zip, Image imageURL) {
        this.userName = userName;
        this.email = email;
        this.email = email1;
        this.zip = zip;
        this.imageURL = imageURL;
    }

    public ImageUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Image getImageURL() {
        return imageURL;
    }

    public void setImageURL(Image imageURL) {
        this.imageURL = imageURL;
    }
}
