package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.builder.ImageBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Image;
import com.winify.cvsi.server.facade.ImageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
@Controller
@RequestMapping(name = "image controller",
        path = "/image",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
    @Autowired
    private ImageFacade imageFacade;

    @GetMapping(path = "/all")
    public HttpEntity<ListDto<ImageDto>> getAllImages(
            @RequestParam Long productId
    ){

        ListDto<ImageDto> imageDtoListDto = new ListDto<ImageDto>();
        List<ImageDto> imageDtoList = new ArrayList<ImageDto>();
        for (int i = 0; i < 10; i++) {
            Image image = new Image();
            image.setImgType("DEFAULT_"+i);
            image.setCreatedDate(new Date());
            ImageBuilder imageBuilder = new ImageBuilder();
            ImageDto imageDto = imageBuilder.getImageDto(image);
            imageDtoList.add(imageDto);
        }

        imageDtoListDto.setList(imageDtoList);
        imageDtoListDto.setError(ErrorEnum.UNKNOWN_ERROR);
        imageDtoListDto.setStatus("OK");

        return new ResponseEntity(imageDtoListDto, HttpStatus.OK);
    }
    @PostMapping(path = "/create")
    public HttpEntity<ServerResponseStatus> setImage(
            @RequestParam String imgType,
            @RequestParam byte[] image,
            @RequestParam @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss") Date createdDate,
            @RequestParam Long productId
    ){

        return new ResponseEntity(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR,"OK"), HttpStatus.OK);
    }

}
