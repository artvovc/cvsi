package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ConversationDao;
import com.winify.cvsi.db.model.Conversation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ConversationDaoImpl extends AbstractDao<Conversation, Long> implements ConversationDao {
    public ConversationDaoImpl() {
        super(Conversation.class);
    }
}
