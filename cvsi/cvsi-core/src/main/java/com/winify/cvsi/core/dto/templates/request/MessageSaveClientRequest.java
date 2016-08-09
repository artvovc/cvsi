package com.winify.cvsi.core.dto.templates.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MessageSaveClientRequest implements Serializable{
    @ApiModelProperty(name = "message", dataType = "String", example = "hello cvsiserver", value = "specify message")
    private String message;
    @ApiModelProperty(name = "createdDate", dataType = "Long", value = "specify created date")
    private Long createdDate;
    @ApiModelProperty(name = "username", dataType = "String", example = "cvsiserver", value = "specify username")
    private String username;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
