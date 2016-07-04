package com.winify.cvsi.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;

import java.sql.Blob;
import java.util.Date;

/**
 * Created by Artemie on 28.06.2016.
 */
public class ImageDto extends ServerResponseStatus {
    private String imgType;
    private Blob img;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private Date createdDate;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
