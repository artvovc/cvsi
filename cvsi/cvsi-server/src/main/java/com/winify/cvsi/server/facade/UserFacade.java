package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.core.service.UserService;
import com.winify.cvsi.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Artemie on 25.06.2016.
 */

@Service
public class UserFacade {
    @Autowired
    private UserService userService;
    public User getUser(Long id){
        return userService.getUser(id);
    }
    public User getUserByMail(String mail){return userService.getUserByMail(mail);}
    public void saveUser(User user){
        userService.saveUser(user);
    }
}
