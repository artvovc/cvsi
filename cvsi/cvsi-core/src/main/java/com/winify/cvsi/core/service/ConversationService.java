package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.ConversationDao;
import com.winify.cvsi.db.model.Conversation;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ConversationService {
    private final ConversationDao conversationDao;

    @Autowired
    public ConversationService(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    @Transactional
    public Long saveConversation(Conversation conversation) {
        return this.conversationDao.save(conversation);
    }

    @Transactional
    public Conversation getConversation(Long conversationId) {
        return this.conversationDao.findById(conversationId);
    }

    @Transactional
    public Set<Conversation> getConversationsCreated(Long userId) {
        return this.conversationDao.getConversationsCreated(userId);
    }

    public Set<Conversation> getConversationsProductOwner(Long userId) {
        return this.conversationDao.getConversationsProductOwner(userId);
    }

    @Transactional
    public Conversation getConversationById(Long conversationId) {
        return this.conversationDao.findById(conversationId);
    }
}
