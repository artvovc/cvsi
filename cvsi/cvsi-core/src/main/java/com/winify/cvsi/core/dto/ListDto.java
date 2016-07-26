package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.*;
@ApiModel(value = "ListDto<T>")
public class ListDto<T> extends ServerResponseStatus implements Serializable {
    @ApiModelProperty(name = "list", dataType = "List<T>", required = true)
    private List<T> list;

    public ListDto() {
        list = new ArrayList<T>();
    }

    public List<T> getList() {
        return list;
    }

    public ListDto<T> setList(Set<T> set) {
        this.list.addAll(set);
        return this;
    }

    public ListDto<T> sortBy(Comparator<T> comparator){
        Collections.sort(this.list,comparator);
        return this;
    }
}
