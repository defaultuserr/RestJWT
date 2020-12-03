package com.Internetx.demo.model;

public class UserModel {
    private String login;

    private String password;
    private int Id;
    private String fname;
    private String lname;
    private String email;

    public UserModel(String login, String password, int id, String fname, String lname, String email) {
        this.login = login;
        this.password = password;
        this.Id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}