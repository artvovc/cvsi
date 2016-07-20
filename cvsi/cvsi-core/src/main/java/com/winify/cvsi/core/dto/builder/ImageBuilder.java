package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.db.model.Image;

import java.util.HashSet;
import java.util.Set;

public class ImageBuilder {
    public ImageDto getImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setCreatedDate(image.getCreatedDate().getTime());
        imageDto.setImage(image.getImage());
        imageDto.setImageType(image.getImageType());
        return imageDto;
    }
    public Set<ImageDto> getImageDto(Set<Image> images){
        Set<ImageDto> imageDtos = new HashSet<>();
        for (Image image : images) {
            ImageDto imageDto = new ImageDto();
            imageDto.setImageType(image.getImageType());
            imageDto.setCreatedDate(image.getCreatedDate().getTime());
            imageDto.setImage(image.getImage());
            imageDtos.add(imageDto);
        }
        return imageDtos;
    }
}
