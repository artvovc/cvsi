package com.winify.cvsi.core.dto.templates.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Artemie on 01.07.2016.
 */
@ApiModel(value = "AuthorizationClientRequest")
public class AuthorizationClientRequest implements Serializable {
    @ApiModelProperty(name = "email", required = true, dataType = "String", example = "example@mail.com", value = "email must be valid")
    private String email;

    @ApiModelProperty(name = "password", required = true, dataType = "String", position = 1, example = "qwerty1234", value = "password must be more than seven digits")
    private String password;

    @ApiModelProperty(name = "phone", required = true, dataType = "String", position = 2, example = "069922158", value = "phone number must be more than seven digits and less than twenty five")
    private String phone;

    @ApiModelProperty(name = "userName", required = true, dataType = "String", position = 3, example = "UserName", value = "user name must be unique")
    private String userName;

    @ApiModelProperty(name = "name", dataType = "String", position = 4, example = "Andy")
    private String name;

    @ApiModelProperty(name = "surname", dataType = "String", position = 5, example = "Tratatori")
    private String surname;

    @ApiModelProperty(name = "createdDate", dataType = "Long", position = 6, example = "1468301773839")
    private Long createdDate;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}
