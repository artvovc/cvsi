package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.enums.ErrorEnum;

/**
 * Created by Artemie on 22.06.2016.
 */
public class CvsiResponse {
    private ErrorEnum error;
    private String status;

    public CvsiResponse() {
        status = "OK";
    }

    public CvsiResponse(ErrorEnum error, String status) {
        this.error = error;
        this.status = status;
    }

    public ErrorEnum getError() {
        return error;
    }

    public void setError(ErrorEnum error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
