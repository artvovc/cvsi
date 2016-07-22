package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.AuthenticationResponseDto;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.request.AuthenticationClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.server.security.SpringSecurityUser;
import com.winify.cvsi.server.security.TokenUtils;
import com.winify.cvsi.server.security.UserDetailsServiceImpl;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Api
@RequestMapping(name = "authentication controller",
        path = "/auth",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    @Autowired
    public AuthController(TokenUtils tokenUtils, @Qualifier("authenticationManager") AuthenticationManager authenticationManager) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpEntity<AuthenticationResponseDto> authenticationRequest(
            @RequestBody @Valid AuthenticationClientRequest authenticationClientRequest
    ) throws AuthenticationException {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationClientRequest.getEmail(),
                        authenticationClientRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SpringSecurityUser user = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = this.tokenUtils.generateToken(user);
        return new ResponseEntity<>(new AuthenticationResponseDto(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), token), HttpStatus.OK);
    }
}
