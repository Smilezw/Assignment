package com.OJ.entity;

public class User {
    String password = null;
    String username = null;
    public User(String a, String b) {
        password = b;
        username = a;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
