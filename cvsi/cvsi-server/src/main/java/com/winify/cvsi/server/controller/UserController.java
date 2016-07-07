package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;
import com.winify.cvsi.core.dto.templates.request.AutorizationClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * Created by Artemie on 25.06.2016.
 */
@Controller
@Api
@RequestMapping(name = "user controller",
        //path = "/user",
        produces = MediaType.APPLICATION_JSON_VALUE//,
        //consumes = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {
    @Autowired
    private UserFacade userFasade;
    final static Logger log = Logger.getLogger(UserController.class);

    @PostMapping(
            path = "/registration",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ServerResponseStatus> saveNewUser(
//            @RequestParam String email,
//            @RequestParam String password,
//            @RequestParam String phone,
//            @RequestParam String userName,
//            @RequestParam String name,
//            @RequestParam String surname,
//            @RequestParam(required = false) Long createdDate
            @RequestBody @Valid AutorizationClientRequest a, HttpServletRequest request
    ){
        printRequest(request);



        User user = new User();
        user.setUsername(a.getUserName());
        user.setName(a.getName());
        user.setSurname(a.getSurname());
        user.setPhone(a.getPhone());
        user.setEmail(a.getEmail());
        user.setPassword(a.getPassword());
        user.setCreatedDate(new Date());
        List<RoleEnum> roleEnumList = new ArrayList<RoleEnum>();
        roleEnumList.add(RoleEnum.ROLE_DEV);
        roleEnumList.add(RoleEnum.ROLE_ADMIN);
        roleEnumList.add(RoleEnum.ROLE_MANAGER);
        roleEnumList.add(RoleEnum.ROLE_USER);
        user.setRoleEnumList(roleEnumList);
        log.info(user.getEmail());
        userFasade.saveUser(user);
        return new ResponseEntity(new ServerResponseStatus(ErrorEnum.SUCCESS,"OK"), HttpStatus.OK);
    }

    @GetMapping(path = "/me")
    public HttpEntity<UserDto> getUserInfo(
            @RequestParam String token
            //HttpServletRequest request
    ){
        //printRequest(request);

        log.info("                        " + token);
        User user = userFasade.getUserByMail(token);
        log.info("                        " + user.getEmail());
        log.info("                        " + user.getPassword());
        log.info("                        " + user.getUsername());
        log.info("                        " + user.getName());
        log.info("                        " + user.getSurname());




        UserBuilder userBuilder = new UserBuilder();
        UserDto userDto = userBuilder.getUser(user);
        userDto.setStatus("OK");
        userDto.setError(ErrorEnum.SUCCESS);

        return new ResponseEntity(userDto,HttpStatus.OK);
    }

    private static void printRequest(HttpServletRequest request) {

        System.out.println("\n\n\tHeaders");

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            System.out.println(headerName + " = " + request.getHeader(headerName));
        }

        System.out.println("\n\n\tParameters");

        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            System.out.println(paramName + " = " + request.getParameter(paramName));
        }

        System.out.println("\n\n\tRow data");
        System.out.println(extractPostRequestBody(request));

        System.out.println("\n\n\t");
        System.out.println("URI " + request.getRequestURI());
        System.out.println("Method " + request.getMethod());
        System.out.println("URL " + request.getRequestURL());
        System.out.println("ServerName " + request.getServerName());
        System.out.println("ServerPort " + request.getServerPort());

    }

    private static String extractPostRequestBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }

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

