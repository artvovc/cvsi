package com.winify.cvsi.server.security;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

/**
 * Created by Artemie on 27.06.2016.
 */
//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger log = Logger.getLogger(CustomAuthenticationProvider.class);

//    @Autowired
//    private CustomUserDetailsService userService;
    private UserDetailsService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = String.valueOf(authentication.getName());
        String pass = String.valueOf(authentication.getCredentials());
        CustomUser user = (CustomUser) userService.loadUserByUsername(email);

        log.info("email: "+email+" password"+pass);
        log.info("email: "+user.getUsername()+" password"+user.getPassword());

        if (user == null || !user.getUsername().equalsIgnoreCase(email)) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!pass.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, pass, authorities);
    }


    public boolean supports(Class<?> authentication) {

        return true;
    }

    public UserDetailsService getUserService() {
        return userService;
    }

    public void setUserService(UserDetailsService userService) {
        this.userService = userService;
    }
}
