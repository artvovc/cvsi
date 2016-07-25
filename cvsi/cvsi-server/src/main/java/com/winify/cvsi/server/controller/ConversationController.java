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

import java.util.*;

@Controller
@Api
@RequestMapping(name = "user controller",
        path = "/conversation",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ConversationController {
    private final ConversationFacade conversationFacade;
    final static Logger log = Logger.getLogger(ConversationController.class);

    @Autowired
    public ConversationController(ConversationFacade conversationFacade) {
        this.conversationFacade = conversationFacade;
    }

    @GetMapping
    public HttpEntity<ListDto<ConversationDto>> getConversation(
    ) {

        User user = new User();
        user.setId(1L);

        User second = new User();
        user.setId(2L);
        user.setName("jon");
        user.setSurname("snow");
        user.setPhone("980999909");
        user.setUsername("jonSNOW");

        Product product = new Product();
        product.setId(1L);
        product.setCreatedDate(new Date());
        product.setLimitDate(new Date());
        product.setDescription("long long long long long long long long long long long long long long long long long long long long long");
        Set<CategoryEnum> categories = new HashSet<>();
        categories.add(CategoryEnum.BORROW);
        categories.add(CategoryEnum.SELL);
        product.setCategories(categories);
        product.setTitle("title_1");
        product.setCurrency(CurrencyEnum.EUR);
        product.setBorrow(true);
        product.setPrice(312L);
        product.setUpdatedDate(new Date());
        product.setUser(user);

        Conversation conversation = new Conversation();
        conversation.setId(1L);
        conversation.setCreatedDate(new Date());
        conversation.setProduct(product);
        conversation.setUser(second);

        Set<Message> messages = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setId((long) i);
            message.setMessage("Noroc_" + i);
            message.setCreatedDate(new Date());
            message.setRead(true);
            messages.add(message);
        }
        conversation.setMessages(messages);

        Set<Conversation> conversations = new HashSet<>();
        conversations.add(conversation);

        Set<ConversationDto> conversationDtoSet = new HashSet<>();

        for (Conversation conversation1 : conversations) {
            ConversationBuilder conversationBuilder = new ConversationBuilder();
            conversationDtoSet.add(conversationBuilder.getConversationDto(conversation1));
        }

        ListDto<ConversationDto> conversationDtos = new ListDto<>();

        conversationDtos.setList(conversationDtoSet);

        conversationDtos.setError(ErrorEnum.UNKNOWN_ERROR);
        conversationDtos.setStatus("OK");


        return new ResponseEntity<>(conversationDtos, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<ServerResponseStatus> setConversation(
            @RequestParam Long productId
    ) {
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR, "OK"), HttpStatus.OK);
    }
}
