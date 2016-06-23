package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.ASimpleDto;
import com.winify.cvsi.core.service.AModelService;
import com.winify.cvsi.db.model.AModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Artemie on 22.06.2016.
 */
@Service
public class AFacade {
    @Autowired
    private AModelService aModelService;

    public ASimpleDto getASimpleDto(Long id){

        AModel aModel = aModelService.getAModel(id);

        ASimpleDto aSimpleDto = new ASimpleDto();
        aSimpleDto.setAge(aModel.getAge());
        aSimpleDto.setFirstName(aModel.getFirstName());
        aSimpleDto.setLastName(aModel.getLastName());

        return aSimpleDto;
    }

    public void saveAModel(){
        AModel aModel = new AModel();
        aModel.setAge(20);
        aModel.setFirstName("Alexei");
        aModel.setLastName("Popa");
        aModel.setCreateTime(new Date());

        aModelService.saveAModel(aModel);

    }
}
