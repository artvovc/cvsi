package com.winify.cvsi.server.controller;

import com.sun.deploy.net.HttpResponse;
import com.winify.cvsi.core.dto.CvsiResponse;
import com.winify.cvsi.core.dto.UserDto;
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
import org.springframework.web.bind.annotation.*;
import sun.security.provider.certpath.OCSPResponse;

/**
 * Created by Artemie on 25.06.2016.
 */
@RestController
@Api
@RequestMapping(name = "user controller",path = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserFacade userFasade;
    final static Logger log = Logger.getLogger(UserController.class);

    @PostMapping(path = "/set/{name}/{surname}")
    public HttpEntity<UserDto> saveNewUser(@PathVariable String name, @PathVariable String surname){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPhone("000000000000");
        user.setEmail("vovc@gmail.com");
        user.setPassword("TREBUIE md5");
        userFasade.saveUser(user);
        return new ResponseEntity<UserDto>(new UserDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"),user),
                HttpStatus.OK);
    }

    @GetMapping(path = "/me/{id}")
    public HttpEntity<UserDto> getUserInfo(@PathVariable Long id){
        return new ResponseEntity<UserDto>(userFasade.getUser(id),HttpStatus.OK);
    }
}

