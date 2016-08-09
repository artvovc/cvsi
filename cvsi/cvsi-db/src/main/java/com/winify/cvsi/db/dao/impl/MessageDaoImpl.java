package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.MessageDao;
import com.winify.cvsi.db.model.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class MessageDaoImpl extends AbstractDao<Message, Long> implements MessageDao {
    public MessageDaoImpl() {
        super(Message.class);
    }

    @Override
    public Long getNotReadMessageCount(Long conversationId) {
        return this.getCurrentSession()
                .createQuery("SELECT count(m) FROM Message AS m WHERE m.conversation.id = :pConversationId AND m.isRead = false", Long.class)
                .setParameter("pConversationId",conversationId)
                .getSingleResult();
    }

    @Override
    public Set<Message> getMessages(Long conversationId) {
        return new HashSet<>(this.getCurrentSession()
                .createQuery("SELECT m FROM Message AS m WHERE m.conversation.id = :pConversationId", this.clazz)
                .setParameter("pConversationId",conversationId)
                .getResultList());
    }
}
