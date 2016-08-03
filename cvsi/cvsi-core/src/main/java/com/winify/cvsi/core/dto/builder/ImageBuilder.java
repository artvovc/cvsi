package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.db.model.Image;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.enums.ImageType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ImageBuilder {

    public Image getImage(Product product, String id, ImageType imageType) {
        Image image = new Image();
        image.setProduct(product);
        image.setCreatedDate(new Date(new Date().getTime() - (1000L * 60)));
        image.setImageType(imageType);
        image.setImage(id);
        return image;
    }

    public ListDto<ImageDto> getImages(Set<Image> images) {
        Set<ImageDto> imageDtos = new HashSet<>();
        images.forEach(image -> {
            ImageDto imageDto = new ImageDto();
            imageDto.setCreatedDate(image.getCreatedDate().getTime());
            imageDto.setImageType(image.getImageType());
            imageDto.setImage("http://192.168.3.191:8080/cvsi-server/image/"+image.getImage());
            imageDtos.add(imageDto);
        });
        ListDto<ImageDto> imageDtoListDto = new ListDto<>();
        imageDtoListDto.setList(imageDtos);
        return imageDtoListDto;
    }
}
