package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.MessageDao;
import com.winify.cvsi.db.model.Message;
import org.springframework.stereotype.Repository;

/**
 * Created by Artemie on 28.06.2016.
 */
@Repository
public class MessageDaoImpl extends AbstractDao<Message, Long> implements MessageDao {
    public MessageDaoImpl() {
        super(Message.class);
    }
}
