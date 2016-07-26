package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "UserDto", description = "contain user minimal necessary information")
public class UserDto extends ServerResponseStatus implements Serializable {
    @ApiModelProperty(name = "id", dataType = "Long", required = true, notes = "contain current user id")
    private Long id;
    @ApiModelProperty(name = "username", dataType = "String", example = "UserName", required = true, notes = "contain current user pseudonym(nickname/username)")
    private String username;
    @ApiModelProperty(name = "name", dataType = "String", example = "Andy", notes = "contain current user name")
    private String name;
    @ApiModelProperty(name = "surname", dataType = "String", example = "Tratatori", notes = "contain current user surname")
    private String surname;
    @ApiModelProperty(name = "phone", dataType = "String", example = "069922158", required = true, notes = "contain current user phone number")
    private String phone;
    @ApiModelProperty(name = "email", dataType = "String", example = "example@mail.com", required = true, notes = "contain current user valid email")
    private String email;
//    @ApiModelProperty(name = "password", dataType = "String", example = "qwerty1234", required = true, notes = "contain current user password")
//    private String password;

    public UserDto() {
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

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
