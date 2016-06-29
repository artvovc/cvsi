package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.CvsiResponse;
import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.CustomUser;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.certpath.OCSPResponse;

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
    public HttpEntity<CvsiResponse> saveNewUser(@ModelAttribute("registrationValue") UserBuilder userBuilder){
        User user = new User();
        user.setUserName(userBuilder.getUserName());
        user.setName(userBuilder.getName());
        user.setSurname(userBuilder.getSurname());
        user.setPhone(userBuilder.getPhone());
        user.setEmail(userBuilder.getEmail());
        user.setPassword(userBuilder.getPassword());

        log.info(user.getEmail());

//        userFasade.saveUser(user);
        return new ResponseEntity(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"), HttpStatus.OK);
    }

    @GetMapping(path = "/me")
    public HttpEntity<UserDto> getUserInfo(@RequestParam(value = "userId",required = false) Integer userId,
                                           @RequestParam(value = "userName",required = false, defaultValue="null") String userName,
                                           @RequestParam(value = "userEmail",required = false, defaultValue="null") String userEmail
                                           ){

        //userFasade.getUser(userId)......

        UserDto userDto = new UserDto();
        userDto.setError(ErrorEnum.UNKNOWN_ERROR);
        userDto.setStatus("OK");
        userDto.setUserName("username");
        userDto.setPassword("1240001230012040120401204");
        userDto.setPhone("000000000");
        userDto.setEmail("joraStyle@gmail.com");
        userDto.setName("jora");
        userDto.setSurname("crit");
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
    }



    @RequestMapping(
            value="/secured/home",
            method = RequestMethod.GET)

    public String securedHome(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser user=null;
        if (principal instanceof CustomUser) {
            user = ((CustomUser)principal);
        }

        String name = user.getUsername();
        model.addAttribute("username", name);
        model.addAttribute("message", "Welcome to the secured page");
        return "home";
    }
}

