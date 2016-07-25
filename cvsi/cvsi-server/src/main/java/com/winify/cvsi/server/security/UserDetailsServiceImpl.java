package com.winify.cvsi.server.security;

import com.winify.cvsi.db.dao.UserDao;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;
    final static Logger log = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        user.setRoles(userDao.getRoles(user.getId()));
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        for (RoleEnum roleEnum : user.getRoles()) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
        }
        return new SpringSecurityUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                null,
                null,
                simpleGrantedAuthorities
        );
    }
}
