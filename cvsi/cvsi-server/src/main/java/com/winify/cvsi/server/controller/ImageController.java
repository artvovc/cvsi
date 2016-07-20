package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.core.dto.SetDto;
import com.winify.cvsi.core.dto.builder.ImageBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Image;
import com.winify.cvsi.db.model.enums.ImageType;
import com.winify.cvsi.server.facade.ImageFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping(name = "image controller",
        path = "/image",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
    private final ImageFacade imageFacade;
    private final static Logger log = Logger.getLogger(ImageController.class);

    @Autowired
    public ImageController(ImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }

    @GetMapping
    public HttpEntity<SetDto<ImageDto>> getImage(
            @RequestParam Long productId
    ) {

        SetDto<ImageDto> images = new SetDto<>();
        Set<ImageDto> imageDtoSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Image image = new Image();
            image.setImageType(ImageType.ANOTHER_IMAGE);
            image.setCreatedDate(new Date());
            ImageBuilder imageBuilder = new ImageBuilder();
            ImageDto imageDto = imageBuilder.getImageDto(image);
            imageDtoSet.add(imageDto);
        }

        images.setSet(imageDtoSet);
        images.setError(ErrorEnum.UNKNOWN_ERROR);
        images.setStatus("OK");

        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<ServerResponseStatus> setImage(
            @RequestParam String imageType,
            @RequestParam byte[] image,
            @RequestParam Long createdDate,
            @RequestParam Long productId
    ) {

        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR, "OK"), HttpStatus.OK);
    }

}
