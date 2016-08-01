package com.winify.cvsi.server.facade;

import com.mongodb.gridfs.GridFSDBFile;
import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.builder.ImageBuilder;
import com.winify.cvsi.core.service.ImageService;
import com.winify.cvsi.db.model.Image;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.enums.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageFacade {
    private final ImageService imageService;
    private final GridFsTemplate gridFsTemplate;

    @Autowired
    public ImageFacade(ImageService imageService, GridFsTemplate gridFsTemplate) {
        this.imageService = imageService;
        this.gridFsTemplate = gridFsTemplate;
    }

    public Long saveImage(Product product, String id, ImageType imageType) {
        return imageService.saveImage(new ImageBuilder().getImage(product, id, imageType));
    }

    public String saveImage(MultipartFile multipartFile, String originalName, String contentType){
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gridFsTemplate.store(in, originalName, contentType).getId().toString();
    }

    public GridFSDBFile getImage(String image) {
        return this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(image)));
    }

    public ListDto<ImageDto> getImages(Long productId, Long userId) {
        return new ImageBuilder().getImages(imageService.getImages(productId,userId));
    }

    public Image getImage(Long imageId) {
        return this.imageService.getImages(imageId);
    }

    public Image getDefaultImage(Long productId, Long userId) {
        return imageService.getDefaultImage(productId,userId);
    }
}
