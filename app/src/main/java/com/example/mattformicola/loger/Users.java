package com.example.mattformicola.loger;

public class Users {

    String email;
    String password;
    String FirstName;
    String LastName;
    String ZipCode ;
    String PhoneNumber;

    public Users(String email, String FirstName, String LastName, String ZipCode,String PhoneNumber) {
        this.email = email;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.ZipCode = ZipCode;
        this.PhoneNumber = PhoneNumber;
    }

}
