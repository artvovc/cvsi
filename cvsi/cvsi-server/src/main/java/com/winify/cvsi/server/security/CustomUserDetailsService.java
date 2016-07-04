package com.winify.cvsi.server.security;

import com.winify.cvsi.core.service.UserService;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artemie on 27.06.2016.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userservice;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userservice.getUserByMail(username);
        CustomUser customUser = new CustomUser(user);
        RoleGrantedAuthority roleGrantedAuthority = new RoleGrantedAuthority();

                roleGrantedAuthority.setName("ROLE_ADMIN");

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        List<SimpleGrantedAuthority> roleGrantedAuthorityList = new ArrayList<SimpleGrantedAuthority>();
        roleGrantedAuthorityList.add(simpleGrantedAuthority);
        customUser.setAuthorities(roleGrantedAuthorityList);

        org.springframework.security.core.userdetails.User userr =
                new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),true,true,true,true,roleGrantedAuthorityList);

        return userr;
    }
}
