package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.request.AuthorizationClientRequest;
import com.winify.cvsi.core.dto.templates.request.UpdateUserClientRequest;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Date;

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
            value = "getUser",
            notes = "get user info by token",
            produces = "application/json",
            httpMethod = "GET",
            response = UserDto.class,
            nickname = "getUser"
    )
    public HttpEntity<UserDto> getUser(
    ) {
        SpringSecurityUser user = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = userFacade.getUserDtoByMail(user.getUsername());
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
            response = ServerResponseStatus.class,
            nickname = "updateUser"
    )
    public HttpEntity<ServerResponseStatus> updateUser(
            @ApiParam(
                    name = "updateUserClientRequest",
                    value = "update user model"
            )
            @RequestBody @Valid UpdateUserClientRequest updateUserClientRequest
    ) {
        ServerResponseStatus serverResponseStatus = null;
        SpringSecurityUser user = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = userFacade.getUser(user.getId());
        userTemp.setUpdatedDate(new Date());
        userTemp.setUsername(updateUserClientRequest.getUsername());
        userTemp.setName(updateUserClientRequest.getName());
        userTemp.setSurname(updateUserClientRequest.getSurname());
        userTemp.setPhone(updateUserClientRequest.getPhone());
        userTemp.setPassword(updateUserClientRequest.getPassword());
        userFacade.updateUser(userTemp);
//        try{
//            userFacade.updateUser(userTemp);
//            serverResponseStatus = new ServerResponseStatus(ErrorEnum.SUCCESS, "OK");
//        }
//        catch (ConstraintViolationException cve){
//            serverResponseStatus = new ServerResponseStatus(ErrorEnum.FAILURE, cve.getMessage());
//            return new ResponseEntity<>(serverResponseStatus, HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
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
        SpringSecurityUser user = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = userFacade.getUser(user.getId());
        if (userTemp != null)
            userFacade.deleteUser(userTemp);
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }
}

