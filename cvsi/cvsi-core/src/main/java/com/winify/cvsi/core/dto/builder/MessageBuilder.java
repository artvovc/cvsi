package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.MessageDto;
import com.winify.cvsi.core.dto.templates.request.MessageSaveClientRequest;
import com.winify.cvsi.db.model.Conversation;
import com.winify.cvsi.db.model.Message;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MessageBuilder {

    public MessageDto getMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setCreatedDate(message.getCreatedDate().getTime());
        messageDto.setMessage(message.getMessage());
        messageDto.setUsername(message.getUsername());
        messageDto.setId(message.getId());
        return messageDto;
    }

    public Message getMessage(Conversation conversation, MessageSaveClientRequest messageSaveClientRequest) {
        Message message = new Message();
        message.setUsername(messageSaveClientRequest.getUsername());
        message.setCreatedDate(messageSaveClientRequest.getCreatedDate() == null ? new Date(new Date().getTime() - (1000L)) : new Date(messageSaveClientRequest.getCreatedDate()));
        message.setMessage(messageSaveClientRequest.getMessage());
        message.setConversation(conversation);
        message.setRead(false);
        return message;
    }

    public Set<MessageDto> getMessageDtos(Set<Message> messages) {
        Set<MessageDto> messageDtos = new HashSet<>();
        messages.forEach(message -> {
            MessageDto messageDto = new MessageDto();
            messageDto.setUsername(message.getUsername());
            messageDto.setId(message.getId());
            messageDto.setMessage(message.getMessage());
            messageDto.setCreatedDate(message.getCreatedDate().getTime());
            messageDtos.add(messageDto);
        });
        return messageDtos;
    }
}
