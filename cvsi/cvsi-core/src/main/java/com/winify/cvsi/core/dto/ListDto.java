package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artemie on 01.07.2016.
 */
public class ListDto<T> extends ServerResponseStatus implements Serializable{

    private List<T> genericList;

    public ListDto(){genericList = new ArrayList<T>();}

    public ListDto(List<T> genericList) {
        this.genericList = genericList;
    }

    public ListDto(ErrorEnum error, String status, List<T> genericList) {
        super(error, status);
        this.genericList = genericList;
    }

    public ListDto(ServerResponseStatus serverResponseStatus, List<T> genericList) {
        super(serverResponseStatus);
        this.genericList = genericList;
    }

    public List<T> getList() {
        return genericList;
    }

    public void setList(List<T> genericList) {
        this.genericList = genericList;
    }
}
