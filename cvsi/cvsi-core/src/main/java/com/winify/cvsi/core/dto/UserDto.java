package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Artemie on 25.06.2016.
 */
@ApiModel(value = "UserDto", description = "contain user minimal necessary information")
public class UserDto extends ServerResponseStatus implements Serializable {
    @ApiModelProperty(name = "id", dataType = "Long",required = true,notes = "contain current user id")
    private Long id;
    @ApiModelProperty(name = "userName", dataType = "String",example = "UserName",required = true,notes = "contain current user pseudonym(nickname/username)")
    private String userName;
    @ApiModelProperty(name = "name", dataType = "String",example = "Andy",notes = "contain current user name")
    private String name;
    @ApiModelProperty(name = "surname", dataType = "String",example = "Tratatori",notes = "contain current user surname")
    private String surname;
    @ApiModelProperty(name = "phone", dataType = "String",example = "069922158",required = true,notes = "contain current user phone number")
    private String phone;
    @ApiModelProperty(name = "email", dataType = "String",example = "example@mail.com",required = true,notes = "contain current user valid email")
    private String email;
    @ApiModelProperty(name = "password", dataType = "String",example = "qwerty1234",required = true,notes = "contain current user password")
    private String password;

    public UserDto() {
    }

    public UserDto(ErrorEnum error, String status, String userName, String name, String surname, String phone, String email, String password) {
        super(error, status);
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public UserDto(ServerResponseStatus serverResponseStatus, String userName, String name, String surname, String phone, String email, String password) {
        super(serverResponseStatus);
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public UserDto(ServerResponseStatus serverResponseStatus, User user) {
        super(serverResponseStatus);
        this.userName = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto setServerResponseStatus(ErrorEnum error, String status) {
        this.setError(error);
        this.setStatus(status);
        return this;
    }
}
