package com.winify.cvsi.server.controller;

import com.mongodb.gridfs.GridFSDBFile;
import com.winify.cvsi.server.facade.ImageFacade;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(name = "image controller",
        path = "/image",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ImageController {
    private final ImageFacade imageFacade;
    private final static Logger log = Logger.getLogger(ImageController.class);

    @Autowired
    public ImageController(ImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }

    @GetMapping(
            path = "/{imageId}"
    )
    public HttpEntity<byte[]> getImage(
            @ApiParam(
                    name = "imageId",
                    required = true,
                    example = "0"
            )
            @PathVariable("imageId") @Valid String imageId
    ) {
        GridFSDBFile imagefile = imageFacade.getImage(imageId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(imagefile.getContentType()));
        headers.setContentLength(imagefile.getLength());
        byte[] image = new byte[0];
        try {
            image = IOUtils.toByteArray(imagefile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

}
