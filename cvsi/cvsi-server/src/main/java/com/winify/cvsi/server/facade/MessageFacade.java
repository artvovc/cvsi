package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageFacade {
    private final MessageService messageService;

    @Autowired
    public MessageFacade(MessageService messageService) {
        this.messageService = messageService;
    }
}
