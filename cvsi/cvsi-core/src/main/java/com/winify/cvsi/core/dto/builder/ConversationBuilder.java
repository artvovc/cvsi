package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ConversationDto;
import com.winify.cvsi.db.model.Conversation;

/**
 * Created by Artemie on 02.07.2016.
 */
public class ConversationBuilder {
    public ConversationDto getConversationDto(Conversation conversation)
    {
        ConversationDto conversationDto = new ConversationDto();
        conversationDto.setMessageList(conversation.getMessageList());
        conversationDto.setTitle(conversation.getProduct().getTitle());
        conversationDto.setReceptorSurname(conversation.getProduct().getUser().getSurname());
        return conversationDto;
    }
}
