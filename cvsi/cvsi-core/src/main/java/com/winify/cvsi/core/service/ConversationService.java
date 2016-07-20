package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.ConversationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private final ConversationDao conversationDao;

    @Autowired
    public ConversationService(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }
}
