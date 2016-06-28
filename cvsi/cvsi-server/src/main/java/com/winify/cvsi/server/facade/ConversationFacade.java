package com.winify.cvsi.server.facade;

import org.springframework.beans.factory.annotation.Autowired;
import com.winify.cvsi.core.service.ConversationService;
import org.springframework.stereotype.Service;

/**
 * Created by Artemie on 28.06.2016.
 */
@Service
public class ConversationFacade {
    @Autowired
    private ConversationService conversationService;
}
