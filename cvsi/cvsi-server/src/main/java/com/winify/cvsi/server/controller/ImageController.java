package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.builder.ImageBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Image;
import com.winify.cvsi.db.model.enums.ImageType;
import com.winify.cvsi.server.facade.ImageFacade;
import com.winify.cvsi.server.facade.ProductFacade;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
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

import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(name = "image controller",
        path = "/image",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
    private final ImageFacade imageFacade;
    private final ProductFacade productFacade;
    private final static Logger log = Logger.getLogger(ImageController.class);

    @Autowired
    public ImageController(ImageFacade imageFacade, ProductFacade productFacade) {
        this.imageFacade = imageFacade;
        this.productFacade = productFacade;
    }

    @GetMapping
    public HttpEntity<ListDto<ImageDto>> getImage(
            @RequestParam Long productId
    ) {

        ListDto<ImageDto> images = new ListDto<>();
        Set<ImageDto> imageDtoSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Image image = new Image();
            image.setImageType(ImageType.ANOTHER_IMAGE);
            image.setCreatedDate(new Date());
            ImageBuilder imageBuilder = new ImageBuilder();
            ImageDto imageDto = imageBuilder.getImageDto(image);
            imageDtoSet.add(imageDto);
        }

        images.setList(imageDtoSet);
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
        String path = "A:/Wallpapers/8.jpg";
        String pathto = "A:/Wallpapers/82.jpg";//IMAGE TYPE JPG PNG RESTU
        Image img = new Image();
        try {
            InputStream is = new FileInputStream(new File(path));
            byte[] bytes = IOUtils.toByteArray(is);
            log.warn(bytes.length);
            Blob blob  = new SerialBlob(bytes);

//            img.setImageType(ImageType.DEFAULT_IMAGE);
//            img.setCreatedDate(new Date());
//            img.setProduct(productFacade.getProductById(10L));
//            img.setImage(blob);

            Image imageBLOB = imageFacade.getImageById(1L);

            Blob imgblob = imageBLOB.getImage();

            InputStream isimagefromdb = imgblob.getBinaryStream();


            OutputStream out = new BufferedOutputStream(new FileOutputStream(pathto));
            IOUtils.copy(isimagefromdb,out);

//out.write(bytes);

            log.warn("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        imageFacade.saveImage(img);
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR, "OK"), HttpStatus.OK);
    }

}
