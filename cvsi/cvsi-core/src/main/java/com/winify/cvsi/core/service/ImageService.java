package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.ImageDao;
import com.winify.cvsi.db.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ImageService {
    private final ImageDao imageDao;

    @Autowired
    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Transactional
    public Long saveImage(Image image) {
        return imageDao.save(image);
    }

    @Transactional
    public Set<Image> getImagesByProductId(Long productId) {
        return imageDao.getImages(productId);
    }

    @Transactional
    public Image getDefaultImage(Long productId, Long userId) {
        return imageDao.getDefaultImage(productId, userId);
    }
}
