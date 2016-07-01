package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;

/**
 * Created by Artemie on 01.07.2016.
 */
public class AuthentificationDto extends ServerResponseStatus {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
