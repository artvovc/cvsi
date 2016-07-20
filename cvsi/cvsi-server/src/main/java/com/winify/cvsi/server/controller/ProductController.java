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
import com.winify.cvsi.db.model.Image;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;
import com.winify.cvsi.server.facade.ImageFacade;
import com.winify.cvsi.server.facade.ProductFacade;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
            @RequestBody @Valid CreateProductClientRequest createProductClientRequest, HttpServletRequest request
    ) {
        Product product = new ProductBuilder().getProduct(createProductClientRequest);
        product.setUser(userFacade.getUserByMail(new TokenUtils().getUsernameFromToken(request.getHeader("X-Auth-Token"))));
        productFacade.saveProduct(product);
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private HttpEntity<SetDto<ProductTemplate>> getProduct(
            @ModelAttribute @Valid ProductSearchTemplate productSearchTemplate,
            HttpServletRequest request
    ) {
        SetDto<ProductTemplate> productSetDto = null;
        if (!productSearchTemplate.getMyProducts()) {
            int n = productSearchTemplate.getCount().intValue();

            if (n != 20) {
                Set<ProductTemplate> productTemplateSet = new HashSet<>();
                for (int i = 0; i < n; ++i) {
                    ProductTemplate productTemplate = new ProductTemplate();
                    productTemplate.setId((long) i);
                    productTemplate.setTitle("title_" + i);
                    productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
                    productTemplate.setCurrency(CurrencyEnum.EUR);
                    productTemplate.setPrice((long) (i * 100));
                    productTemplate.setBorrow(true);
                    productTemplate.setLimitDate(new Date().getTime());
                    productTemplate.setUserName("vasea_" + i);

                    Set<CategoryEnum> categories = new HashSet<>();
                    categories.add(CategoryEnum.BORROW);
                    categories.add(CategoryEnum.SELL);
                    categories.add(CategoryEnum.BUY);

                    productTemplate.setCategories(categories);
                    productTemplate.setCreatedDate(new Date().getTime());
                    productTemplate.setUpdatedDate(new Date().getTime());
                    productTemplateSet.add(productTemplate);
                }

                productSetDto = new SetDto<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), productTemplateSet);

            } else {
                Set<ProductTemplate> productTemplateSet = new HashSet<>();
                for (int i = 0; i < 5; ++i) {
                    ProductTemplate productTemplate = new ProductTemplate();
                    productTemplate.setId((long) i);
                    productTemplate.setTitle("title_" + i);
                    productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
                    productTemplate.setCurrency(CurrencyEnum.EUR);
                    productTemplate.setPrice((long) (i * 100));
                    productTemplate.setBorrow(true);
                    productTemplate.setLimitDate(new Date().getTime() + i * 1000);
                    productTemplate.setUserName("vasea_" + i);

                    Set<CategoryEnum> categories = new HashSet<>();
                    categories.add(CategoryEnum.BORROW);

                    productTemplate.setCategories(categories);
                    productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
                    productTemplate.setUpdatedDate(new Date().getTime());
                    productTemplateSet.add(productTemplate);
                }
                for (int i = 5; i < 10; ++i) {
                    ProductTemplate productTemplate = new ProductTemplate();
                    productTemplate.setId((long) i);
                    productTemplate.setTitle("title_" + i);
                    productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
                    productTemplate.setCurrency(CurrencyEnum.EUR);
                    productTemplate.setPrice((long) (i * 100));
                    productTemplate.setBorrow(true);
                    productTemplate.setLimitDate(new Date().getTime() + i * 1000);
                    productTemplate.setUserName("vasea_" + i);

                    Set<CategoryEnum> categories = new HashSet<>();
                    categories.add(CategoryEnum.SELL);

                    productTemplate.setCategories(categories);
                    productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
                    productTemplate.setUpdatedDate(new Date().getTime());
                    productTemplateSet.add(productTemplate);
                }
                for (int i = 10; i < 15; ++i) {
                    ProductTemplate productTemplate = new ProductTemplate();
                    productTemplate.setId((long) i);
                    productTemplate.setTitle("title_" + i);
                    productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
                    productTemplate.setCurrency(CurrencyEnum.MDL);
                    productTemplate.setPrice((long) (i * 100));
                    productTemplate.setBorrow(false);
                    productTemplate.setLimitDate(new Date().getTime() + i * 1000);
                    productTemplate.setUserName("vasea_" + i);

                    Set<CategoryEnum> categories = new HashSet<>();
                    categories.add(CategoryEnum.BUY);

                    productTemplate.setCategories(categories);
                    productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
                    productTemplate.setUpdatedDate(new Date().getTime());
                    productTemplateSet.add(productTemplate);
                }
                for (int i = 15; i < 20; ++i) {
                    ProductTemplate productTemplate = new ProductTemplate();
                    productTemplate.setId((long) i);
                    productTemplate.setTitle("title_" + i);
                    productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
                    productTemplate.setCurrency(CurrencyEnum.USD);
                    productTemplate.setPrice((long) (i * 100));
                    productTemplate.setBorrow(false);
                    productTemplate.setLimitDate(new Date().getTime() + i * 1000);
                    productTemplate.setUserName("vasea_" + i);


                    Set<CategoryEnum> categories = new HashSet<>();
                    categories.add(CategoryEnum.BORROW);
                    categories.add(CategoryEnum.SELL);

                    productTemplate.setCategories(categories);
                    productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
                    productTemplate.setUpdatedDate(new Date().getTime());
                    productTemplateSet.add(productTemplate);
                }


                productSetDto = new SetDto<>(
                        new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR, "OK"),
                        productTemplateSet);
            }
        }else {
            Long userId = userFacade.getUserByMail(new TokenUtils().getUsernameFromToken(request.getHeader("X-Auth-Token"))).getId();
            productSetDto.setSet(new ProductBuilder().getProductTemplates(productFacade.getMyProducts(userId)));
            productSetDto.setError(ErrorEnum.SUCCESS);
            productSetDto.setStatus("OK");
        }

        return new ResponseEntity<>(productSetDto, HttpStatus.OK);
    }


}
