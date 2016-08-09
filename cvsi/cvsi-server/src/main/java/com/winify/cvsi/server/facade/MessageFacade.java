package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.MessageDto;
import com.winify.cvsi.core.dto.builder.MessageBuilder;
import com.winify.cvsi.core.dto.templates.request.MessageSaveClientRequest;
import com.winify.cvsi.core.service.MessageService;
import com.winify.cvsi.db.model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessageFacade {
    private final MessageService messageService;

    @Autowired
    public MessageFacade(MessageService messageService) {
        this.messageService = messageService;
    }

    public Long getNotReadMessageCount(Long conversationId) {
        return this.messageService.getNotReadMessageCount(conversationId);
    }

    public Long saveMessage(Conversation conversation, MessageSaveClientRequest messageSaveClientRequest) {
        return this.messageService.saveMessage(new MessageBuilder().getMessage(conversation, messageSaveClientRequest));
    }

    public MessageDto getMessageDtoById(Long messageId) {
        return new MessageBuilder().getMessageDto(this.messageService.getMessageById(messageId));
    }

    public Set<MessageDto> getMessages(Long conversationId) {
        return new MessageBuilder().getMessageDtos(this.messageService.getMessages(conversationId));
    }
}
