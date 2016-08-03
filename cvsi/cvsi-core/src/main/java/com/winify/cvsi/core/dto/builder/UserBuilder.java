package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.templates.request.RegistrationClientRequest;
import com.winify.cvsi.core.dto.templates.request.UserUpdateClientRequest;
import com.winify.cvsi.db.model.Registration;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

public class UserBuilder {

    public User getUserRegistrationData(UserDto userDto) {
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

    public Registration getUserRegistrationData(RegistrationClientRequest registrationClientRequest) {
        Registration registration = new Registration();
        registration.setUsername(registrationClientRequest.getUsername());
        registration.setName(registrationClientRequest.getName());
        registration.setSurname(registrationClientRequest.getSurname());
        registration.setPhone(registrationClientRequest.getPhone());
        registration.setEmail(registrationClientRequest.getEmail());
        registration.setPassword(registrationClientRequest.getPassword());
        registration.setHash(DigestUtils.md2Hex(registration.getEmail()+registration.getUsername()+registration.getPhone()+registration.getPassword()));
        registration.setRequestCreatedDate(registrationClientRequest.getCreatedDate()==null?new Date(new Date().getTime()-(1000L*60)):new Date(registrationClientRequest.getCreatedDate()));
        return registration;
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

    public User getUpdatedUser(User user, String id) {
        user.setImage(id);
        return user;
    }

    public User getUpdatedUser(User user) {
        user.setOnline(true);
        return user;
    }

    public User getUser(Registration registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setName(registration.getName());
        user.setSurname(registration.getSurname());
        user.setPhone(registration.getPhone());
        user.setEmail(registration.getEmail());
        user.setPassword(registration.getPassword());
        user.setCreatedDate(registration.getRequestCreatedDate());
        user.setArchived(false);
        user.setOnline(false);
        Set<RoleEnum> roles = new HashSet<>();
        roles.add(RoleEnum.ROLE_USER);
        user.setRoles(roles);
        return user;
    }
}
