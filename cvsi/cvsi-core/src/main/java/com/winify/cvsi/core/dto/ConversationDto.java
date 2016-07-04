package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.db.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artemie on 02.07.2016.
 */
public class ConversationDto extends ServerResponseStatus{
    private String receptorSurname;
    private String title;
    private List<Message> messageList = new ArrayList<Message>();

    public String getReceptorSurname() {
        return receptorSurname;
    }

    public void setReceptorSurname(String receptorSurname) {
        this.receptorSurname = receptorSurname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
