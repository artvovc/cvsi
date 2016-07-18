package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.AuthenticationResponseDto;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.request.AuthenticationClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.server.security.TokenUtils;
import com.winify.cvsi.server.security.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

/**
 * Created by Artemie on 18.07.2016.
 */
@Controller
@Api
@RequestMapping(name = "authentication controller",
        path = "/auth",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> authenticationRequest(
            @RequestBody @Valid AuthenticationClientRequest authenticationClientRequest
    ) throws AuthenticationException{
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationClientRequest.getEmail(),
                        authenticationClientRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationClientRequest.getEmail());
        String token = this.tokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDto(new ServerResponseStatus(ErrorEnum.SUCCESS,"OK"),token));
    }
}
