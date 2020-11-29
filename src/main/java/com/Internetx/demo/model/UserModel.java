package com.Internetx.demo.model;

public class UserModel {
    private String loginName;

    private String password;
    private int Id;
    private String f_name;
    private String l_name;
    private String email;

    public UserModel(String loginName, String password, int id, String f_name, String l_name, String email) {
        this.loginName = loginName;
        this.password = password;
        Id = id;
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


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}