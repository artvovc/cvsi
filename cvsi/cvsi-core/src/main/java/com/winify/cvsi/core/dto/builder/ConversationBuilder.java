package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ConversationDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.db.model.Conversation;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ConversationBuilder {
    public ConversationDto getConversationDto(Conversation conversation) {
        ConversationDto conversationDto = new ConversationDto();
        conversationDto.setId(conversation.getId());
        conversationDto.setReceptorUsername(conversation.getProduct().getUser().getUsername());
        conversationDto.setTitle(conversation.getProduct().getTitle());
//        conversationDto.setMessages(conversation.getMessages());
        return conversationDto;
    }

    public Conversation getConversation(User user, Product product) {
        Conversation conversation = new Conversation();
        conversation.setCreatedDate(new Date(new Date().getTime() - (1000L*60)));
        conversation.setProduct(product);
        conversation.setUser(user);
        return conversation;
    }

    public ListDto<ConversationDto> getConversationDtosCreated(Set<Conversation> conversations) {
        ListDto<ConversationDto> conversationDtos = new ListDto<>();
        conversations.forEach(conversation -> {
            ConversationDto conversationDto = new ConversationDto();
            conversationDto.setId(conversation.getId());
            conversationDto.setTitle(conversation.getProduct().getTitle());
            conversationDto.setReceptorUsername(conversation.getProduct().getUser().getUsername());
            conversationDto.setCreatedDate(conversation.getCreatedDate().getTime());
            conversationDtos.getList().add(conversationDto);
        });
        return conversationDtos;
    }

    public List<ConversationDto> getConversationDtosProductOwner(Set<Conversation> conversations) {
        List<ConversationDto> conversationDtos = new ArrayList<>();
        conversations.forEach(conversation -> {
            ConversationDto conversationDto = new ConversationDto();
            conversationDto.setId(conversation.getId());
            conversationDto.setTitle(conversation.getProduct().getTitle());
            conversationDto.setReceptorUsername(conversation.getUser().getUsername());
            conversationDto.setCreatedDate(conversation.getCreatedDate().getTime());
            conversationDtos.add(conversationDto);
        });
        return conversationDtos;
    }
}
