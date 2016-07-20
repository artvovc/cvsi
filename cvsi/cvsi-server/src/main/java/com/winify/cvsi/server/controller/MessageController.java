package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.SetDto;
import com.winify.cvsi.core.dto.MessageDto;
import com.winify.cvsi.core.dto.builder.MessageBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Message;
import com.winify.cvsi.server.facade.MessageFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;

@Controller
@RequestMapping(name = "message controller",
        path = "/message",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    private final MessageFacade messageFacade;
    private final static Logger log = Logger.getLogger(MessageController.class);

    @Autowired
    public MessageController(MessageFacade messageFacade) {
        this.messageFacade = messageFacade;
    }

    @GetMapping
    public HttpEntity<SetDto<MessageDto>> getMessage(
            @RequestParam("conversation_id") Long conversation_id
    ) {
        SetDto<MessageDto> messages = new SetDto<>();
        messages.setSet(new HashSet<>());
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setId((long) i);
            message.setMessage("Noroc_" + i);
            message.setCreatedDate(new Date());
            message.setRead(true);
            MessageBuilder messageBuilder = new MessageBuilder();
            messages.getSet().add(messageBuilder.getMessageDto(message));
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<ServerResponseStatus> setMessage(
            @RequestParam Long conversationId
    ) {
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR, "OK"), HttpStatus.OK);
    }

}
