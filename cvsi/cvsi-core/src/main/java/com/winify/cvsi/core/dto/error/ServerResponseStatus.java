package com.winify.cvsi.core.dto.error;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Artemie on 22.06.2016.
 */
@ApiModel(value = "ServerResponseStatus", description = "server response: error and status")
public class ServerResponseStatus implements Serializable {
    @ApiModelProperty(name = "error", dataType = "String", example = "UNKNOWN_ERROR", value = "specify errors")
    private ErrorEnum error;
    @ApiModelProperty(name = "status", dataType = "String", example = "can't save user, incorrect email", value = "get error status")
    private String status;

    public ServerResponseStatus() {
        status = "OK";
    }

    public ServerResponseStatus(ErrorEnum error, String status) {
        this.error = error;
        this.status = status;
    }

    public ServerResponseStatus(ServerResponseStatus serverResponseStatus) {
        this.error = serverResponseStatus.getError();
        this.status = serverResponseStatus.getStatus();
    }

    public ErrorEnum getError() {
        return error;
    }

    public void setError(ErrorEnum error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
