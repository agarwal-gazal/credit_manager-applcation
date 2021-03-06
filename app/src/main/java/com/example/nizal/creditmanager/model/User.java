package com.example.nizal.creditmanager.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String credit;

    public User(int id, String name, String email, String credit) {
        this.id= id;
        this.name=name;
        this.email=email;
        this.credit=credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
