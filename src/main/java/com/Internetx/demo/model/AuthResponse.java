package com.Internetx.demo.model;

public class AuthResponse {


    public AuthResponse(String token) {
        this.token = token;
    }
    public AuthResponse(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

}
