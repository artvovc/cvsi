package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Artemie on 28.06.2016.
 */
public class MessageDto extends ServerResponseStatus implements Serializable {
    private String message;
    private Long createdDate;
    private Boolean isRead;

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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
