package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.ASimpleDto;
import com.winify.cvsi.core.service.AModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.winify.cvsi.core.dto.builder.AModelBuilder.convertToDto;
import static com.winify.cvsi.core.dto.builder.AModelBuilder.convertToModel;

@Service
public class AFacade {
    @Autowired
    private AModelService aModelService;

    public ASimpleDto getAModel(Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return convertToDto(aModelService.getAModel(id));
    }

    public void saveAModel(ASimpleDto aSimpleDto){
        aSimpleDto.setCreateTime(new Date().getTime());

        aModelService.saveAModel(convertToModel(aSimpleDto));
    }
}
