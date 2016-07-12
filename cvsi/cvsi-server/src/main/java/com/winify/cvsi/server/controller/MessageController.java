package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.MessageDto;
import com.winify.cvsi.core.dto.builder.MessageBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Message;
import com.winify.cvsi.server.facade.ImageFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Artemie on 28.06.2016.
 */
@Controller
@RequestMapping(name = "message controller",
        path = "/message",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    @Autowired
    private ImageFacade imageFacade;
    private final static Logger log = Logger.getLogger(MessageController.class);

    @GetMapping
    public HttpEntity<ListDto<MessageDto>> getMessage(
            @RequestParam("conversation_id") Long conversation_id
    ){
        ListDto<MessageDto> messageListDto = new ListDto<>();
        messageListDto.setList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setId((long) i);
            message.setMessage("Noroc_"+i);
            message.setCreatedDate(new Date());
            message.setRead(true);
            MessageBuilder messageBuilder = new MessageBuilder();
            messageListDto.getList().add(messageBuilder.getMessageDto(message));
        }
        return new ResponseEntity<>(messageListDto, HttpStatus.OK);
    }
    @PostMapping
    public HttpEntity<ServerResponseStatus> setMessage(
            @RequestParam Long conversationId
    ){
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR,"OK"), HttpStatus.OK);
    }

}
