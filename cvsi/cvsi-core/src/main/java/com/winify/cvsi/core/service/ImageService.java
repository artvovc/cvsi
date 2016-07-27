package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.ImageDao;
import com.winify.cvsi.db.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {
    private final ImageDao imageDao;

    @Autowired
    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Transactional
    public void saveImage(Image image) {
        imageDao.save(image);
    }

    @Transactional
    public Image getImageById(Long imageId) {
        return imageDao.findById(imageId);
    }
}
