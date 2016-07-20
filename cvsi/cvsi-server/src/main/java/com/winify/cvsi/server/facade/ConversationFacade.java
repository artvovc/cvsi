package com.winify.cvsi.server.facade;

import org.springframework.beans.factory.annotation.Autowired;
import com.winify.cvsi.core.service.ConversationService;
import org.springframework.stereotype.Service;

@Service
public class ConversationFacade {
    private final ConversationService conversationService;

    @Autowired
    public ConversationFacade(ConversationService conversationService) {
        this.conversationService = conversationService;
    }
}
