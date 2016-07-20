package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ImageDao;
import com.winify.cvsi.db.model.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl extends AbstractDao<Image, Long> implements ImageDao {
    public ImageDaoImpl() {
        super(Image.class);
    }
}
