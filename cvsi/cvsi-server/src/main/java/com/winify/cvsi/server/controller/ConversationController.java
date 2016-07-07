package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ConversationDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.builder.ConversationBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Conversation;
import com.winify.cvsi.db.model.Message;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;
import com.winify.cvsi.server.facade.ConversationFacade;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 29.06.2016.
 */
@Controller
@Api
@RequestMapping(name = "user controller",
        path = "/conversation",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ConversationController {
    @Autowired
    private ConversationFacade conversationFacade;
    final static Logger log = Logger.getLogger(ConversationController.class);

    @GetMapping(path = "/all")
    public HttpEntity<ListDto<ConversationDto>> getConversation(
    ){

        User user = new User();
        user.setId(new Long(1));

        User second = new User();
        user.setId(new Long(2));
        user.setName("jon");
        user.setSurname("snow");
        user.setPhone("980999909");
        user.setUsername("jonSNOW");

        Product product = new Product();
        product.setId(new Long(1));
        product.setCreatedDate(new Date());
        product.setLimitDate(new Date());
        product.setDescription("long long long long long long long long long long long long long long long long long long long long long");
        List<CategoryEnum> categoryEnumList = new ArrayList<CategoryEnum>();
        categoryEnumList.add(CategoryEnum.BORROW);
        categoryEnumList.add(CategoryEnum.SELL);
        product.setCategoryEnumList(categoryEnumList);
        product.setTitle("title_1");
        product.setCurrency(CurrencyEnum.EUR);
        product.setIsBorrow(new Boolean(true));
        product.setPrice(new Long(312));
        product.setUpdatedDate(new Date());
        product.setUser(user);

        Conversation conversation = new Conversation();
        conversation.setId(new Long(1));
        conversation.setCreatedDate(new Date());
        conversation.setProduct(product);
        conversation.setUser(second);

        List<Message> messageList = new ArrayList<Message>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setId(new Long(i));
            message.setMessage("Noroc_"+i);
            message.setCreatedDate(new Date());
            message.setRead(new Boolean(true));
            messageList.add(message);
        }
        conversation.setMessageList(messageList);

        List<Conversation> conversationList = new ArrayList<Conversation>();
        conversationList.add(conversation);

        List<ConversationDto> conversationDtoList = new ArrayList<ConversationDto>();

        for (Conversation conversation1 : conversationList) {
            ConversationBuilder conversationBuilder = new ConversationBuilder();
            conversationDtoList.add(conversationBuilder.getConversationDto(conversation1));
        }

        ListDto<ConversationDto> conversationDtos = new ListDto<ConversationDto>();

        conversationDtos.setList(conversationDtoList);

        conversationDtos.setError(ErrorEnum.UNKNOWN_ERROR);
        conversationDtos.setStatus("OK");


        return new ResponseEntity(conversationDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public HttpEntity<ServerResponseStatus> setConversation(
            @RequestParam Long productId
    ){
        return new ResponseEntity(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR,"OK"),HttpStatus.OK);
    }
}
