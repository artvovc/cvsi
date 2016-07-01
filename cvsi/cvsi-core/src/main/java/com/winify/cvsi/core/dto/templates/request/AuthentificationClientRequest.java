package com.winify.cvsi.core.dto.templates.request;

/**
 * Created by Artemie on 01.07.2016.
 */
public class AuthentificationClientRequest {

    private String email;
    private String password;

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
