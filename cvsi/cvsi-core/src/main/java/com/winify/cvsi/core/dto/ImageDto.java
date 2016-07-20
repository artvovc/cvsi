package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.db.model.enums.ImageType;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

public class ImageDto extends ServerResponseStatus implements Serializable {
    private ImageType imageType;
    private Blob image;
    private Long createdDate;

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}
