package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.request.AuthorizationClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.SpringSecurityUser;
import com.winify.cvsi.server.security.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Api(
        value = "/user",
        description = "services for users",
        produces = "application/json"
)
@RequestMapping(
        path = "/user",
        name = "user controller",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {
    private final UserFacade userFacade;
    private final static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "saveNewUser",
            notes = "save new user into database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "POST",
            response = ServerResponseStatus.class,
            nickname = "saveNewUser")
    public HttpEntity<ServerResponseStatus> saveNewUser(
            @ApiParam(
                    name = "authorizationClientRequest",
                    required = true,
                    value = "registration model"
            )
            @RequestBody @Valid AuthorizationClientRequest authorizationClientRequest
    ) {
        userFacade.saveUser(authorizationClientRequest);
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(
            value = "getUserInfo",
            notes = "get user info by token",
            produces = "application/json",
            httpMethod = "GET",
            response = UserDto.class,
            nickname = "getUserInfo")
    public HttpEntity<UserDto> getUserInfo(
    ) {
        SpringSecurityUser springSecurityUser = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = userFacade.getUserDtoByMail(springSecurityUser.getEmail());
        userDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}

