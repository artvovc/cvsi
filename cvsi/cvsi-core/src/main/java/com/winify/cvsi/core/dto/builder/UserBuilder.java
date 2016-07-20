package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.templates.request.AuthorizationClientRequest;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;

import java.util.*;

public class UserBuilder {

    public User getUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        return user;
    }

    public UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        return userDto;
    }

    public User getUser(AuthorizationClientRequest authorizationClientRequest) {
        User user = new User();
        user.setUsername(authorizationClientRequest.getUsername());
        user.setName(authorizationClientRequest.getName());
        user.setSurname(authorizationClientRequest.getSurname());
        user.setPhone(authorizationClientRequest.getPhone());
        user.setEmail(authorizationClientRequest.getEmail());
        user.setPassword(authorizationClientRequest.getPassword());
        user.setCreatedDate(new Date());
        Set<RoleEnum> roles = new HashSet<>();
        roles.add(RoleEnum.ROLE_USER);
        user.setRoles(roles);
        user.setOnline(false);
        return user;
    }
}
