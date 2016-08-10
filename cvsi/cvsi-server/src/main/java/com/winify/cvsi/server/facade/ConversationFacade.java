package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.ConversationDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.builder.ConversationBuilder;
import com.winify.cvsi.db.model.Conversation;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.winify.cvsi.core.service.ConversationService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class ConversationFacade {
    private final ConversationService conversationService;

    @Autowired
    public ConversationFacade(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    public Long saveConversation(User user, Product product) {
        return conversationService.saveConversation(new ConversationBuilder().getConversation(user, product));
    }

    public ConversationDto getConversationDto(Long conversationId) {
        return new ConversationBuilder().getConversationDto(conversationService.getConversation(conversationId));
    }

    public ListDto<ConversationDto> getConversationDtosCreated(Long userId) {
        return new ConversationBuilder().getConversationDtosCreated(this.conversationService.getConversationsCreated(userId));
    }

    public Set<ConversationDto> getConversationDtosProductOwner(Long userId) {
        return new ConversationBuilder().getConversationDtosProductOwner(this.conversationService.getConversationsProductOwner(userId));
    }

    public Conversation getConversationById(Long conversationId) {
        return this.conversationService.getConversationById(conversationId);
    }
}
