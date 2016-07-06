package com.winify.cvsi.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;

import java.util.Date;

/**
 * Created by Artemie on 28.06.2016.
 */
public class MessageDto extends ServerResponseStatus {
    private String message;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private Date createdDate;
    private Boolean isRead;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
