package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.MessageDto;
import com.winify.cvsi.db.model.Message;

public class MessageBuilder {

    public MessageDto getMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setCreatedDate(message.getCreatedDate().getTime());
        messageDto.setMessage(message.getMessage());
        messageDto.setRead(message.getRead());
        return messageDto;
    }
}
