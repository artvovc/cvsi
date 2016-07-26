package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.ProductDto;
import com.winify.cvsi.core.dto.builder.ImageBuilder;
import com.winify.cvsi.core.dto.builder.ProductBuilder;
import com.winify.cvsi.core.dto.comparator.PriceComparator;
import com.winify.cvsi.core.dto.templates.request.ProductSearchClientRequest;
import com.winify.cvsi.core.dto.templates.response.ProductTemplateResponse;
import com.winify.cvsi.core.dto.templates.request.ProductCreateClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.facade.ImageFacade;
import com.winify.cvsi.server.facade.ProductFacade;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.userdetail.CustomUserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public HttpEntity<ListDto<ImageDto>> getImages(
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
        ListDto<ImageDto> images = new ListDto<>();
        images.setList(new ImageBuilder().getImageDto(product.getImages()));
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "postProduct",
            notes = "save new product into database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "POST",
            response = ProductDto.class,
            nickname = "postProduct"
    )
    public HttpEntity<ProductDto> postProduct(
            @ApiParam(
                    name = "productCreateClientRequest",
                    required = true,
                    value = "create product model"
            )
            @RequestBody @Valid ProductCreateClientRequest productCreateClientRequest
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = new ProductBuilder().getProduct(productCreateClientRequest);
        product.setUser(userFacade.getUser(user.getId()));
        Long productId = productFacade.saveProduct(product);
        ProductDto productDto = productFacade.getProductDtoById(productId);
        productDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private HttpEntity<ListDto<ProductTemplateResponse>> getProduct(
            @ModelAttribute @Valid ProductSearchClientRequest productSearchClientRequest
    ) {
        ListDto<ProductTemplateResponse> productListDto = new ListDto<>();
        if (productSearchClientRequest.getMyProducts()) {
            CustomUserDetails user = new CustomUserDetails();
            try {
                user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            } catch (Exception exp) {
                productListDto.setStatus(exp.getMessage());
            } finally {
                productListDto.setList(productFacade.getMyProducts(user.getId())).sortBy(new PriceComparator());
            }
        } else
            productListDto.setList(productFacade.getProducts(productSearchClientRequest)).sortBy(new PriceComparator());
        productListDto.setError(ErrorEnum.SUCCESS);
//        productListDto.setStatus("OK");
        return new ResponseEntity<>(productListDto, HttpStatus.OK);
    }

}
