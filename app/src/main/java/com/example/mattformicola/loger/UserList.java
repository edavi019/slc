package com.example.mattformicola.loger;

public class UserList    {

    private String userName,email,phone,zip;
    private int radio;

    public UserList() {

    }

    public UserList(String userName, String email, String phone, String zip, int radio) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.zip = zip;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
