package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.AModelDao;
import com.winify.cvsi.db.model.AModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Artemie on 22.06.2016.
 */
@Service
public class AModelService {
    @Autowired
    private AModelDao aModelDao;

    public AModel getAModel(Long id){
        return aModelDao.findById(id);
    }

    @Transactional
    public void saveAModel(AModel aModel){
        aModelDao.save(aModel);
    }
}
