package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;
import com.winify.cvsi.core.dto.templates.request.AutorizationClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.facade.UserFacade;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Artemie on 25.06.2016.
 */
@Controller
@Api
@RequestMapping(name = "user controller",
        //path = "/user",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserFacade userFasade;
    final static Logger log = Logger.getLogger(UserController.class);

    @PostMapping(path = "/registration")
    public HttpEntity<ServerResponseStatus> saveNewUser(
            @ModelAttribute("registrationValue") AutorizationClientRequest autorizationClientRequest
    ){
        User user = new User();
        user.setUsername(autorizationClientRequest.getUserName());
        user.setName(autorizationClientRequest.getName());
        user.setSurname(autorizationClientRequest.getSurname());
        user.setPhone(autorizationClientRequest.getPhone());
        user.setEmail(autorizationClientRequest.getEmail());
        user.setPassword(autorizationClientRequest.getPassword());

        log.info(user.getEmail());

//        userFasade.saveUser(user);
        return new ResponseEntity(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR,"OK"), HttpStatus.OK);
    }

    @GetMapping(path = "/me")
    public HttpEntity<UserDto> getUserInfo(
    ){

        //userFasade.getUser(userId)......
        User user = new User();
        user.setName("jora");
        user.setSurname("afanasiev");
        user.setUsername("volandemort");
        user.setEmail("user@gmail.com");
        user.setPassword("pinaCeNuAmParola");
        user.setPhone("069999999");

        UserBuilder userBuilder = new UserBuilder();
        UserDto userDto = userBuilder.getUser(user);


        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
    }
}

