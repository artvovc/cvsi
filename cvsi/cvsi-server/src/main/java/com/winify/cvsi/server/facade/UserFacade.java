package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.CvsiResponse;
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
    public UserDto getUser(Long id){
        User user = userService.getUser(id);
        return new UserDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword()
                );
    }
    public void saveUser(User user){
        userService.saveUser(user);
    }
}
