package com.example.mattformicola.loger.Model;

import android.media.Image;

public class User {

    //private String email;
    private String firstName;
    private String lastName;
    public String phoneNumber;
    public String userName;
    public String zip;
    public Image imageURL;
    public int radio;

     User(String firstName, String lastName, String phoneNumber, String userName, String zip, Image imageURL, int radio) {
        //this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.zip = zip;
        this.imageURL = imageURL;
        this.radio = radio;
    }


/*    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        userName = userName;
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

    public void setImage(Image imageURL) {
        this.imageURL = imageURL;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
}
