package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.Message;

import java.util.Set;

public interface MessageDao extends CrudOperations<Message, Long> {
    Long getNotReadMessageCount(Long conversationId);

    Set<Message> getMessages(Long conversationId);
}
