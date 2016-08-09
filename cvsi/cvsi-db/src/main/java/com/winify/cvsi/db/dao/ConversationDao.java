package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.Conversation;

import java.util.Set;

public interface ConversationDao extends CrudOperations<Conversation, Long> {
    Set<Conversation> getConversationsCreated(Long userId);

    Set<Conversation> getConversationsProductOwner(Long userId);
}
