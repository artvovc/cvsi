package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;

import com.winify.cvsi.core.dto.templates.request.AuthorizationClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;
import com.winify.cvsi.server.facade.UserFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * Created by Artemie on 25.06.2016.
 */
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
    @Autowired
    private UserFacade userFacade;
    private final static Logger log = Logger.getLogger(UserController.class);

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
            @RequestBody @Valid AuthorizationClientRequest authorizationClientRequest,
            HttpServletRequest request
    ) {
        userFacade.saveUser(new UserBuilder().getUser(authorizationClientRequest));
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

    @GetMapping(
    )
    @ApiOperation(
            value = "getUserInfo",
            notes = "get user info by token",
            produces = "application/json",
            httpMethod = "GET",
            response = UserDto.class,
            nickname = "getUserInfo")
    public HttpEntity<UserDto> getUserInfo(
            @ApiParam(
                    name = "token",
                    required = true,
                    value = "unique temporal token")
            @RequestParam String token
    ) {
        return new ResponseEntity<>(new UserBuilder().getUserDto(userFacade.getUserByMail(token)).setServerResponseStatus(ErrorEnum.SUCCESS,"OK"), HttpStatus.OK);
    }

//    private static void printRequest(HttpServletRequest request) {
//
//        System.out.println("\n\n\tHeaders");
//
//        Enumeration headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = (String) headerNames.nextElement();
//            System.out.println(headerName + " = " + request.getHeader(headerName));
//        }
//
//        System.out.println("\n\n\tParameters");
//
//        Enumeration params = request.getParameterNames();
//        while (params.hasMoreElements()) {
//            String paramName = (String) params.nextElement();
//            System.out.println(paramName + " = " + request.getParameter(paramName));
//        }
//
//        System.out.println("\n\n\tRow data");
//        System.out.println(extractPostRequestBody(request));
//
//        System.out.println("\n\n\t");
//        System.out.println("URI " + request.getRequestURI());
//        System.out.println("Method " + request.getMethod());
//        System.out.println("URL " + request.getRequestURL());
//        System.out.println("ServerName " + request.getServerName());
//        System.out.println("ServerPort " + request.getServerPort());
//
//    }
//
//    private static String extractPostRequestBody(HttpServletRequest request) {
//        if ("POST".equalsIgnoreCase(request.getMethod())) {
//            Scanner s = null;
//            try {
//                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            assert s != null;
//            return s.hasNext() ? s.next() : "";
//        }
//        return "";
//    }

//    public static void reauthenticate(final String username, final String password) {
//        UserDetailsService userDetailsService = getBean("userDetailsService");
//        UserCache userCache = getBean("userCache");
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
//                userDetails, password == null ? userDetails.getPassword() : password, userDetails.getAuthorities()));
//        userCache.removeUserFromCache(username);
//    }
}

