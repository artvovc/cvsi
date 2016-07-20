package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ConversationDao;
import com.winify.cvsi.db.model.Conversation;
import org.springframework.stereotype.Repository;

@Repository
public class ConversationDaoImpl extends AbstractDao<Conversation, Long> implements ConversationDao {
    public ConversationDaoImpl() {
        super(Conversation.class);
    }
}
