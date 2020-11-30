package com.Internetx.demo.model;

public class AuthRequest {
    public AuthRequest(String username, String password) {
        this.login = username;
        this.password = password;
    }
    public AuthRequest() {

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

    private String login;
    private String password;

}
