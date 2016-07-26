package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.RegistrationDto;
import com.winify.cvsi.core.dto.templates.request.LoginClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.userdetail.CustomUserDetails;
import com.winify.cvsi.server.security.token.TokenUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Api
@RequestMapping(name = "login controller",
        path = "/login",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final UserFacade userFacade;

    @Autowired
    public LoginController(TokenUtils tokenUtils, @Qualifier("authenticationManager") AuthenticationManager authenticationManager, UserFacade userFacade) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userFacade = userFacade;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpEntity<RegistrationDto> authenticationRequest(
            @RequestBody @Valid LoginClientRequest loginClientRequest
    ) throws AuthenticationException {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginClientRequest.getEmail(),
                        loginClientRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = this.tokenUtils.generateToken(user);
        RegistrationDto registrationDto = new RegistrationDto();
        if (token != null) {
            User userTemp = userFacade.getUser(user.getId());
            userFacade.updateUser(userTemp);
            registrationDto.setToken(token);
            registrationDto.setUserDto(userFacade.getUserDto(user.getId()));
            registrationDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");
        }
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }
}
