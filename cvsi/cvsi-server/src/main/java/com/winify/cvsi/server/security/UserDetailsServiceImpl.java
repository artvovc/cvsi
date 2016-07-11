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
import java.util.List;

/**
 * Created by Artemie on 05.07.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    final static Logger log = Logger.getLogger(UserDetailsServiceImpl.class);

    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        log.info(mail);
        User user = userDao.findByEmail(mail);

        if(user == null){
            throw new UsernameNotFoundException(mail);
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (RoleEnum roleEnum : user.getRoleEnumList()) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                simpleGrantedAuthorities
        );
    }
}
