package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.MessageDao;
import com.winify.cvsi.db.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class MessageService {
    private final MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Transactional
    public Long getNotReadMessageCount(Long conversationId) {
        return this.messageDao.getNotReadMessageCount(conversationId);
    }

    @Transactional
    public Long saveMessage(Message message) {
        return this.messageDao.save(message);
    }

    @Transactional
    public Message getMessageById(Long messageId) {
        return this.messageDao.findById(messageId);
    }

    @Transactional
    public Set<Message> getMessages(Long conversationId) {
        return this.messageDao.getMessages(conversationId);
    }
}
