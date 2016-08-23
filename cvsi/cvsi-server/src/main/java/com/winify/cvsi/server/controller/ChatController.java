package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.templates.request.ChatRequest;
import com.winify.cvsi.core.dto.templates.response.ChatResponse;
import org.springframework.core.SpringVersion;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {

    @MessageMapping("/questions/{id}")
    @SendTo("/topic/questions/{id}")
    public ChatResponse hellowin(
            @DestinationVariable String id,
            ChatRequest chatRequest
    ){
        return new ChatResponse(SpringVersion.getVersion()+" version  "+id+":   FROM: "+ chatRequest.getName()+" current date:"+new Date().getTime());
    }
}
