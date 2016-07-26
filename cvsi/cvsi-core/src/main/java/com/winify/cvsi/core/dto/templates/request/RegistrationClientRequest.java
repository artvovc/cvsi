package com.winify.cvsi.core.dto.templates.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "RegistrationClientRequest")
public class RegistrationClientRequest implements Serializable {
    @ApiModelProperty(name = "email", required = true, dataType = "String", example = "example@mail.com", value = "email must be valid")
    private String email;
    @ApiModelProperty(name = "password", required = true, dataType = "String", position = 1, example = "qwerty1234", value = "password must be more than seven digits")
    private String password;
    @ApiModelProperty(name = "phone", required = true, dataType = "String", position = 2, example = "069922158", value = "phone number must be more than seven digits and less than twenty five")
    private String phone;
    @ApiModelProperty(name = "username", required = true, dataType = "String", position = 3, example = "Username", value = "user name must be unique")
    private String username;
    @ApiModelProperty(name = "name", dataType = "String", position = 4, example = "Andy")
    private String name;
    @ApiModelProperty(name = "surname", dataType = "String", position = 5, example = "Tratatori")
    private String surname;
    @ApiModelProperty(name = "createdDate", dataType = "Long", position = 6, example = "0")
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
