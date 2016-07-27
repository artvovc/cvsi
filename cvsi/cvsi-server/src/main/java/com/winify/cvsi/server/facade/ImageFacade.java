package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.service.ImageService;
import com.winify.cvsi.db.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageFacade {
    private final ImageService imageService;

    @Autowired
    public ImageFacade(ImageService imageService) {
        this.imageService = imageService;
    }

    public void saveImage(Image image) {
        this.imageService.saveImage(image);
    }

    public Image getImageById(Long imageId) {
        return this.imageService.getImageById(imageId);
    }
}
