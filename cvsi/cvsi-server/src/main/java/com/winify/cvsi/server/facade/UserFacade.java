package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.service.UserService;
import com.winify.cvsi.db.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {
    private final UserService userService;
    Logger log = Logger.getLogger(UserFacade.class);

    @Autowired
    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public User getUser(Long id) {
        return userService.getUser(id);
    }

    public User getUserByMail(String mail) {
        log.warn(mail);
        return userService.getUserByMail(mail);
    }

    public void saveUser(User user) {
        userService.saveUser(user);
    }
}
