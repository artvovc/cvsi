package com.winify.cvsi.core.dto.templates.request;

public class AuthenticationClientRequest {
    private String email;
    private String password;

    public AuthenticationClientRequest() {
    }

    public AuthenticationClientRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
