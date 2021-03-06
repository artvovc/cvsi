package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "MessageDto")
public class MessageDto extends ServerResponseStatus implements Serializable {
    @ApiModelProperty(name = "id", dataType = "Long", example = "0", value = "specify message id")
    private Long id;
    @ApiModelProperty(name = "message", dataType = "String", example = "hello cvsiserver", value = "specify message")
    private String message;
    @ApiModelProperty(name = "createdDate", dataType = "Long", value = "specify created date")
    private Long createdDate;
//    private Boolean isRead;
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

//    public Boolean getRead() {
//        return isRead;
//    }
//
//    public void setRead(Boolean read) {
//        isRead = read;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
