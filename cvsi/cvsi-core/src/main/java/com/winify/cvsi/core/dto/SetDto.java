package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SetDto<T> extends ServerResponseStatus implements Serializable {

    private Set<T> set;

    public SetDto() {
        set = new HashSet<T>();
    }

    public SetDto(ServerResponseStatus serverResponseStatus, Set<T> set) {
        super(serverResponseStatus);
        this.set = set;
    }

    public Set<T> getSet() {
        return set;
    }

    public void setSet(Set<T> set) {
        this.set = set;
    }
}
