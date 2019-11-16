package com.example.dagger.model;

public class UserModel {

    private int id;
    private String username;
    private String email;
    private String website;

    public UserModel() {

    }

    public UserModel(int id, String username, String email, String website) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
