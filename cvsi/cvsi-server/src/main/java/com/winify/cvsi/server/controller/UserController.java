package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;
import com.winify.cvsi.core.dto.templates.request.AutorizationClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//            @RequestParam String email,
//            @RequestParam String password,
//            @RequestParam String phone,
//            @RequestParam String userName,
//            @RequestParam String name,
//            @RequestParam String surname,
//            @RequestParam(required = false) Long createdDate
              @RequestBody AutorizationClientRequest a
    ){
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
        return new ResponseEntity(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR,"OK"), HttpStatus.OK);
    }

    @GetMapping(path = "/me")
    public HttpEntity<UserDto> getUserInfo(
            @RequestParam String token
    ){

        User user = userFasade.getUserByMail(token);


        UserBuilder userBuilder = new UserBuilder();
        UserDto userDto = userBuilder.getUser(user);
        userDto.setStatus("OK your username: "+ userDto.getUserName());
        userDto.setError(ErrorEnum.UNKNOWN_ERROR);

        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
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

