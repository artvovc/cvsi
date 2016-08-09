package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.MessageDto;
import com.winify.cvsi.core.dto.templates.request.MessageSaveClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.server.facade.ConversationFacade;
import com.winify.cvsi.server.facade.MessageFacade;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(name = "message controller",
        path = "/message",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    private final MessageFacade messageFacade;
    private final ConversationFacade conversationFacade;
    private final static Logger log = Logger.getLogger(MessageController.class);

    @Autowired
    public MessageController(MessageFacade messageFacade, ConversationFacade conversationFacade) {
        this.messageFacade = messageFacade;
        this.conversationFacade = conversationFacade;
    }

    @GetMapping(
            path = "/{conversationId}"
    )
    public HttpEntity<ListDto<MessageDto>> getMessage(
            @PathVariable Long conversationId
    ) {
        ListDto<MessageDto> messages = new ListDto<>();
        messages.setList(messageFacade.getMessages(conversationId));
        messages.setError(ErrorEnum.SUCCESS);
        messages.setStatus("OK");
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping(
            path = "/{conversationId}"
    )
    @ApiOperation(
            value = "postMessage",
            notes = "save message to database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "POST",
            response = MessageDto.class,
            nickname = "postMessage"
    )
    public HttpEntity<MessageDto> postMessage(
            @PathVariable Long conversationId,
            @RequestBody @Valid MessageSaveClientRequest messageSaveClientRequest
    ) {
        Long messageId = messageFacade.saveMessage(conversationFacade.getConversationById(conversationId), messageSaveClientRequest);
        MessageDto message = messageFacade.getMessageDtoById(messageId);
        message.setError(ErrorEnum.SUCCESS);
        message.setStatus("OK");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
