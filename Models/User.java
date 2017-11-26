package com.example.vonga.oderfoodapp.Models;

/**
 * Created by VoNga on 11/26/2017.
 */

public class User {
    private String name;
    private String phone;
    private String pass;

    public User(String name, String phone, String pass) {
        this.name = name;
        this.phone = phone;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
