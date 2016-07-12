package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.templates.request.AuthorizationClientRequest;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
public class UserBuilder {

    public User getUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        return user;
    }
    public UserDto getUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        return userDto;
    }
    public User getUser(AuthorizationClientRequest authorizationClientRequest)
    {
        User user = new User();
        user.setUsername(authorizationClientRequest.getUserName());
        user.setName(authorizationClientRequest.getName());
        user.setSurname(authorizationClientRequest.getSurname());
        user.setPhone(authorizationClientRequest.getPhone());
        user.setEmail(authorizationClientRequest.getEmail());
        user.setPassword(authorizationClientRequest.getPassword());
        user.setCreatedDate(new Date());
        List<RoleEnum> roleEnumList = new ArrayList<>();
        roleEnumList.add(RoleEnum.ROLE_USER);
        user.setRoleEnumList(roleEnumList);
        return user;
    }
}
