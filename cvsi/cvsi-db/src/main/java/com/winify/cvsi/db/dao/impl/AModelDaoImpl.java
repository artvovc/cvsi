package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.AModelDao;
import com.winify.cvsi.db.model.AModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AModelDaoImpl extends AbstractDao<AModel, Long> implements AModelDao {
    public AModelDaoImpl() {
        super(AModel.class);
    }
}
