package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ConversationDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.comparator.CreatedDateComparator2;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.facade.ConversationFacade;
import com.winify.cvsi.server.facade.MessageFacade;
import com.winify.cvsi.server.facade.ProductFacade;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.userdetail.CustomUserDetails;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@Api
@RequestMapping(name = "user controller",
        path = "/conversation",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ConversationController {
    private final ConversationFacade conversationFacade;
    private final UserFacade userFacade;
    private final ProductFacade productFacade;
    private final MessageFacade messageFacade;

    final static Logger log = Logger.getLogger(ConversationController.class);

    @Autowired
    public ConversationController(ConversationFacade conversationFacade, ProductFacade productFacade, UserFacade userFacade, MessageFacade messageFacade) {
        this.conversationFacade = conversationFacade;
        this.productFacade = productFacade;
        this.userFacade = userFacade;
        this.messageFacade = messageFacade;
    }

    @GetMapping
    public HttpEntity<ListDto<ConversationDto>> getConversation(
            Principal principal
    ) {
        ListDto<ConversationDto> conversations = conversationFacade.getConversationDtosCreated(((CustomUserDetails) principal).getId());
        conversations.setList(conversationFacade.getConversationDtosProductOwner(((CustomUserDetails) principal).getId())).sortBy(new CreatedDateComparator2());
        conversations.getList().forEach(conversationDto -> conversationDto.setNotReadMessages(messageFacade.getNotReadMessageCount(conversationDto.getId())));
        conversations.setError(ErrorEnum.SUCCESS);
        conversations.setStatus("OK");
        return new ResponseEntity<>(conversations, HttpStatus.OK);
    }

    @PostMapping(
            path = "/{productId}"
    )
    public HttpEntity<ConversationDto> postConversation(
            @PathVariable Long productId,
            Principal principal
    ) {
        User usertemp = userFacade.getUser(((CustomUserDetails) principal).getId());
        Product product = productFacade.getProductById(productId);
        Long conversationId = conversationFacade.saveConversation(usertemp, product);
        ConversationDto conversationDto = conversationFacade.getConversationDto(conversationId);
        conversationDto.setNotReadMessages(messageFacade.getNotReadMessageCount(conversationId));
        conversationDto.setStatus("OK");
        conversationDto.setError(ErrorEnum.SUCCESS);
        return new ResponseEntity<>(conversationDto, HttpStatus.OK);
    }
}
