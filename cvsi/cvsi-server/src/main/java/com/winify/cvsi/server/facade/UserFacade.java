package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;
import com.winify.cvsi.core.dto.templates.request.AuthorizationClientRequest;
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

    public UserDto getUserDtoByMail(String email) {
        return new UserBuilder().getUserDto(userService.getUserByMail(email));
    }

    public User getUserByMail(String email) {
        return userService.getUserByMail(email);
    }

    public void saveUser(AuthorizationClientRequest user) {
        userService.saveUser(new UserBuilder().getUser(user));
    }
}
