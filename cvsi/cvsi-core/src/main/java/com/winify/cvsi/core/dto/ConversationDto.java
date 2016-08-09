package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.db.model.Message;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ConversationDto extends ServerResponseStatus implements Serializable {
    private Long id;
    private String receptorUsername;
    private String title;
    private Long notReadMessages;
    private Long createdDate;
//    private Set<Message> messages = new HashSet<>();


    public ConversationDto() {
        notReadMessages = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceptorUsername() {
        return receptorUsername;
    }

    public void setReceptorUsername(String receptorUsername) {
        this.receptorUsername = receptorUsername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getNotReadMessages() {
        return notReadMessages;
    }

    public void setNotReadMessages(Long notReadMessages) {
        this.notReadMessages = notReadMessages;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    //    public Set<Message> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(Set<Message> messages) {
//        this.messages = messages;
//    }
}
