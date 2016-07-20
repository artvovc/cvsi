package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;

import java.io.Serializable;

public class AuthenticationResponseDto extends ServerResponseStatus implements Serializable {

    private String token;

    public AuthenticationResponseDto() {
    }

    public AuthenticationResponseDto(ErrorEnum error, String status) {
        super(error, status);
    }

    public AuthenticationResponseDto(ServerResponseStatus serverResponseStatus) {
        super(serverResponseStatus);
    }

    public AuthenticationResponseDto(String token) {
        this.token = token;
    }

    public AuthenticationResponseDto(ErrorEnum error, String status, String token) {
        super(error, status);
        this.token = token;
    }

    public AuthenticationResponseDto(ServerResponseStatus serverResponseStatus, String token) {
        super(serverResponseStatus);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
