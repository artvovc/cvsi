package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.core.dto.SetDto;
import com.winify.cvsi.core.dto.builder.ImageBuilder;
import com.winify.cvsi.core.dto.builder.ProductBuilder;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.ProductSearchTemplate;
import com.winify.cvsi.core.dto.templates.ProductTemplate;
import com.winify.cvsi.core.dto.templates.request.CreateProductClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.server.facade.ImageFacade;
import com.winify.cvsi.server.facade.ProductFacade;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.SpringSecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Api
@RequestMapping(
        name = "product controller",
        path = "/product",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProductController {
    private final ProductFacade productFacade;
    private final UserFacade userFacade;
    private final ImageFacade imageFacade;
    private final static Logger log = Logger.getLogger(ProductController.class);

    @Autowired
    public ProductController(UserFacade userFacade, ProductFacade productFacade, ImageFacade imageFacade) {
        this.userFacade = userFacade;
        this.productFacade = productFacade;
        this.imageFacade = imageFacade;
    }

    @GetMapping(
            path = "/{productId}/image",
            produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public HttpEntity<SetDto<ImageDto>> getImages(
            @ApiParam(
                    name = "productId",
                    required = true
            )
            @PathVariable Long productId,
//            @ApiParam(
//                    name = "imageId"
//            )
//            @PathVariable Long imageId,
            HttpServletRequest request
    ) {
        Product product = productFacade.getProductById(productId);
        SetDto<ImageDto> images = new SetDto<>();
        images.setSet(new ImageBuilder().getImageDto(product.getImages()));
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ServerResponseStatus> saveProduct(
            @ApiParam(
                    name = "createProductClientRequest",
                    required = true,
                    value = "create product model"
            )
            @RequestBody @Valid CreateProductClientRequest createProductClientRequest
    ) {
        SpringSecurityUser springSecurityUser = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = new ProductBuilder().getProduct(createProductClientRequest);
        product.setUser(userFacade.getUserByMail(springSecurityUser.getEmail()));
        productFacade.saveProduct(product);
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private HttpEntity<SetDto<ProductTemplate>> getProduct(
            @ModelAttribute @Valid ProductSearchTemplate productSearchTemplate
    ) {
        SetDto<ProductTemplate> productSetDto = new SetDto<>();
        if (productSearchTemplate.getMyProducts()){
            SpringSecurityUser springSecurityUser = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            productSetDto.setSet(productFacade.getMyProducts(springSecurityUser.getId()));
        } else
            productSetDto.setSet(productFacade.getProducts(productSearchTemplate));
        productSetDto.setError(ErrorEnum.SUCCESS);
        productSetDto.setStatus("OK");
        return new ResponseEntity<>(productSetDto, HttpStatus.OK);
    }


}
