package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ConversationDao;
import com.winify.cvsi.db.model.Conversation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class ConversationDaoImpl extends AbstractDao<Conversation, Long> implements ConversationDao {
    public ConversationDaoImpl() {
        super(Conversation.class);
    }

    @Override
    public Set<Conversation> getConversationsCreated(Long userId) {
        return new HashSet<>(
                this.getCurrentSession()
                        .createQuery("SELECT c FROM Conversation AS c WHERE c.user.id = :pUserId", this.clazz)
                        .setParameter("pUserId", userId)
                        .getResultList()
        );
    }

    @Override
    public Set<Conversation> getConversationsProductOwner(Long userId) {
        return new HashSet<>(
                this.getCurrentSession()
                        .createQuery("SELECT c FROM Conversation AS c WHERE c.product.user.id = :pUserId", this.clazz)
                        .setParameter("pUserId", userId)
                        .getResultList()
        );
    }
}
