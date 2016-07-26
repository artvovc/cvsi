package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "ServerResponseStatus", description = "server response: error and status")
public class RegistrationDto extends ServerResponseStatus implements Serializable {
    @ApiModelProperty(name = "token", dataType = "String", value = "token for communicate with server")
    private String token;
    @ApiModelProperty(name = "userDto", dataType = "UserDto", value = "current user")
    private UserDto userDto;

    public RegistrationDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegistrationDto setServerResponseStatus(ErrorEnum errorEnum, String status){
        this.setError(errorEnum);
        this.setStatus(status);
        return this;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
