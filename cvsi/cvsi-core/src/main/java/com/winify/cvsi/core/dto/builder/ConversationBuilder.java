package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ConversationDto;
import com.winify.cvsi.db.model.Conversation;

public class ConversationBuilder {
    public ConversationDto getConversationDto(Conversation conversation) {
        ConversationDto conversationDto = new ConversationDto();
        conversationDto.setId(conversation.getId());
        conversationDto.setMessages(conversation.getMessages());
        conversationDto.setTitle(conversation.getProduct().getTitle());
        conversationDto.setReceptorUsername(conversation.getProduct().getUser().getUsername());
        return conversationDto;
    }
}
