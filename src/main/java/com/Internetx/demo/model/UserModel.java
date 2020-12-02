package com.Internetx.demo.model;

public class UserModel {
    private String login;

    private String password;
    private int Id;
    private String f_name;
    private String l_name;
    private String email;

    public UserModel(String login, String password, int id, String f_name, String l_name, String email) {
        this.login = login;
        this.password = password;
        this.Id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
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