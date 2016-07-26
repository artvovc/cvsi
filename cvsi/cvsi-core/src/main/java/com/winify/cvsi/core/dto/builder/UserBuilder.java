package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.templates.request.RegistrationClientRequest;
import com.winify.cvsi.core.dto.templates.request.UserUpdateClientRequest;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;

import java.util.*;

public class UserBuilder {

    public User getUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
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
        userDto.setPhone(user.getPhone());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        return userDto;
    }

    public UserDto getUserDto(
            Long id,
            String username,
            String email,
            String phone,
            String name,
            String surname
    ) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setPhone(phone);
        userDto.setName(name);
        userDto.setSurname(surname);
        return userDto;
    }

    public User getUser(RegistrationClientRequest registrationClientRequest) {
        User user = new User();
        user.setUsername(registrationClientRequest.getUsername());
        user.setName(registrationClientRequest.getName());
        user.setSurname(registrationClientRequest.getSurname());
        user.setPhone(registrationClientRequest.getPhone());
        user.setEmail(registrationClientRequest.getEmail());
        user.setPassword(registrationClientRequest.getPassword());
        user.setCreatedDate(new Date(new Date().getTime()-(1000L*60)));
        user.setArchived(false);
        user.setOnline(false);
        Set<RoleEnum> roles = new HashSet<>();
        roles.add(RoleEnum.ROLE_USER);
        user.setRoles(roles);
        return user;
    }

    public User getUpdatedUser(User user, UserUpdateClientRequest userUpdateClientRequest) {
        user.setUsername(userUpdateClientRequest.getUsername());
        user.setName(userUpdateClientRequest.getName());
        user.setSurname(userUpdateClientRequest.getSurname());
        user.setPhone(userUpdateClientRequest.getPhone());
        user.setPassword(userUpdateClientRequest.getPassword());
        user.setUpdatedDate(new Date(new Date().getTime()-(1000L*60)));
        return user;
    }

    public User getUpdatedUser(User user) {
        user.setOnline(true);
        return user;
    }
}
