package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.db.model.Image;

/**
 * Created by Artemie on 04.07.2016.
 */
public class ImageBuilder {
    public ImageDto getImageDto(Image image)
    {
        ImageDto imageDto = new ImageDto();
        imageDto.setCreatedDate(image.getCreatedDate());
        imageDto.setImg(image.getImg());
        imageDto.setImgType(image.getImgType());
        return imageDto;
    }
}
