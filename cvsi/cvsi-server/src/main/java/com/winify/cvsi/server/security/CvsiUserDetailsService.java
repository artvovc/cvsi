package com.winify.cvsi.server.security;

import com.winify.cvsi.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Artemie on 27.06.2016.
 */
public class CvsiUserDetailsService implements UserDetailsService{
    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
