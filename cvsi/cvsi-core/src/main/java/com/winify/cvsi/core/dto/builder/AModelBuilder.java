package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ASimpleDto;
import com.winify.cvsi.db.model.AModel;

import java.util.Date;

public class AModelBuilder {

    public static ASimpleDto convertToDto(AModel aModel) {
        ASimpleDto aSimpleDto = new ASimpleDto();
        if (aModel != null) {
            aSimpleDto.setId(aModel.getId());
            aSimpleDto.setAge(aModel.getAge());
            aSimpleDto.setFirstName(aModel.getFirstName());
            aSimpleDto.setLastName(aModel.getLastName());
            aSimpleDto.setCreateTime(aModel.getCreateTime().getTime());
        }
        return aSimpleDto;
    }

    public static AModel convertToModel(ASimpleDto dto) {
        AModel aModel = new AModel();

        if (dto == null) {
            return null;
        }
        aModel.setId(dto.getId());
        aModel.setAge(dto.getAge());
        aModel.setFirstName(dto.getFirstName());
        aModel.setLastName(dto.getLastName());
        if (dto.getCreateTime() != null)
            aModel.setCreateTime(new Date(dto.getCreateTime()));
        return aModel;
    }
}
