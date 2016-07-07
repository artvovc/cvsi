package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;

import java.sql.Blob;
import java.util.Date;

/**
 * Created by Artemie on 28.06.2016.
 */
public class ImageDto extends ServerResponseStatus {
    private String imgType;
    private Blob img;
    private Long createdDate;

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}
