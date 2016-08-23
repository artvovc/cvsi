package com.winify.cvsi.server.controller;



import com.winify.cvsi.core.dto.templates.request.Hello;
import com.winify.cvsi.core.dto.templates.response.HelloResponse;
import org.springframework.core.SpringVersion;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class HzController {
    @MessageMapping("/questions/{id}")
    @SendTo("/topic/questions/{id}")
    public HelloResponse hellowin(
            @DestinationVariable String id,
            Hello hello
    ){
//        return new Hello("work ! "+conversationId.toString()+" current date:"+new Date().getTime());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new HelloResponse(SpringVersion.getVersion()+" version iopta "+id+":   FROM: "+hello.getName()+" current date:"+new Date().getTime());
    }
}
