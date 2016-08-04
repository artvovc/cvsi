package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.builder.UserBuilder;
import com.winify.cvsi.core.dto.templates.request.RegistrationClientRequest;
import com.winify.cvsi.core.dto.templates.request.UserUpdateClientRequest;
import com.winify.cvsi.core.service.MailService;
import com.winify.cvsi.core.service.RegistrationService;
import com.winify.cvsi.core.service.UserService;
import com.winify.cvsi.db.model.Registration;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.security.userdetail.CustomUserDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {
    private final UserService userService;
    private final MailService mailService;
    private final RegistrationService registrationService;
    Logger log = Logger.getLogger(UserFacade.class);

    @Autowired
    public UserFacade(UserService userService, MailService mailService, RegistrationService registrationService) {
        this.userService = userService;
        this.mailService = mailService;
        this.registrationService = registrationService;
    }

    public User getUser(Long id) {
        return userService.getUser(id);
    }

    public UserDto getUserDto(Long id) {
        return new UserBuilder().getUserDto(userService.getUser(id));
    }

    public UserDto getUserDto(CustomUserDetails user) {
        return new UserBuilder().getUserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getName(),
                user.getSurname()
        );
    }
    @Deprecated
    public UserDto getUserDtoByMail(String email) {
        return new UserBuilder().getUserDto(userService.getUserByMail(email));
    }
    @Deprecated
    public User getUserByMail(String email) {
        return userService.getUserByMail(email);
    }

    public Long saveUserRegistrationData(RegistrationClientRequest user) {
        return registrationService.saveRegistrationData(new UserBuilder().getUserRegistrationData(user));
    }

    public void updateUser(User user, UserUpdateClientRequest userUpdateClientRequest) {
        userService.updateUser(new UserBuilder().getUpdatedUser(user, userUpdateClientRequest));
    }

    public void updateUser(User user) {
        userService.updateUser(new UserBuilder().getUpdatedUser(user));
    }
    @Deprecated
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }

    public void updateUser(User user, String id) {
        userService.updateUser(new UserBuilder().getUpdatedUser(user, id));
    }

    public void sendMail(RegistrationClientRequest registrationClientRequest){
        this.mailService.sendMail(registrationClientRequest);
    }

    public Registration getRegistrationDataByHash(String hash) {
        return this.registrationService.getRegistrationDataByHash(hash);
    }

    public Long saveUser(Registration registration) {
        return this.userService.saveUser(new UserBuilder().getUser(registration));
    }

    public void deleteUserRegistrationData(Registration registration) {
        this.registrationService.deleteUserRegistrationData(registration);
    }

    public UserDto getUserDto(User user) {
        return new UserBuilder().getUserDto(user);
    }
}
