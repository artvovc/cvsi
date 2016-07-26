package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.request.RegistrationClientRequest;
import com.winify.cvsi.core.dto.templates.request.UserUpdateClientRequest;
import com.winify.cvsi.core.dto.validator.RegistrationValidator;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.userdetail.CustomUserDetails;
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
import org.springframework.web.bind.annotation.*;

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
            value = "postUser",
            notes = "save new user into database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "POST",
            response = ServerResponseStatus.class,
            nickname = "postUser"
    )
    public HttpEntity<ServerResponseStatus> postUser(
            @ApiParam(
                    name = "registrationClientRequest",
                    required = true,
                    value = "registration model"
            )
            @RequestBody @Valid RegistrationClientRequest registrationClientRequest
    ) {
        RegistrationValidator registrationValidator = new RegistrationValidator();
        if (registrationValidator.isValid(registrationClientRequest))
            try {
                userFacade.saveUser(registrationClientRequest);
            } catch (Exception exp) {
                registrationValidator.setMessage(exp.getMessage());
            }
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, registrationValidator.getMessage()), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(
            value = "getUser",
            notes = "get user info by token",
            produces = "application/json",
            httpMethod = "GET",
            response = UserDto.class,
            nickname = "getUser"
    )
    public HttpEntity<UserDto> getUser(
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = userFacade.getUserDto(user);
        userDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(
            value = "updateUser",
            notes = "update user into database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "PUT",
            response = UserDto.class,
            nickname = "updateUser"
    )
    public HttpEntity<UserDto> updateUser(
            @ApiParam(
                    name = "userUpdateClientRequest",
                    value = "update user model"
            )
            @RequestBody @Valid UserUpdateClientRequest userUpdateClientRequest
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = userFacade.getUser(user.getId());
        userFacade.updateUser(userTemp, userUpdateClientRequest);
        UserDto userDto = userFacade.getUserDto(user.getId());
        userDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(
            value = "deleteUser",
            notes = "delete user from database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "DELETE",
            response = ServerResponseStatus.class,
            nickname = "deleteUser"
    )
    public HttpEntity<ServerResponseStatus> deleteUser(
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = userFacade.getUser(user.getId());
        if (userTemp != null){
            userTemp.setArchived(true);
            userFacade.updateUser(userTemp);
        }
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }
}

