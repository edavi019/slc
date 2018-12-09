package com.example.mattformicola.loger;

public class User {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userName;
    private String zip;
    private int radio;


    User(String email, String FirstName, String LastName, String UserName, String PhoneNumber, String zip, int radio) {
        this.email = email;
        this.firstName = FirstName;
        this.lastName = LastName;
        this.userName = UserName;
        this.phoneNumber = PhoneNumber;
        this.zip = zip;
        this.radio = radio;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    String getZip() {
        return zip;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    void setZip(String zip) {
        this.zip = zip;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
}


