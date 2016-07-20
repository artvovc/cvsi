package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.AModelDao;
import com.winify.cvsi.db.model.AModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AModelService {
    private final AModelDao aModelDao;

    @Autowired
    public AModelService(AModelDao aModelDao) {
        this.aModelDao = aModelDao;
    }

    @Transactional
    public AModel getAModel(Long id) {
        return aModelDao.findById(id);
    }

    @Transactional
    public void saveAModel(AModel aModel) {
        aModelDao.save(aModel);
    }
}
