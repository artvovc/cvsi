package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.ProductSearchTemplate;
import com.winify.cvsi.core.dto.templates.ProductTemplate;
import com.winify.cvsi.core.dto.templates.request.ProductCreateClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;
import com.winify.cvsi.server.facade.ProductFacade;
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

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
@Controller
@Api
@RequestMapping(
        name = "product controller",
        path = "/product",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    private final static Logger log = Logger.getLogger(ProductController.class);



    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    @RequestMapping(method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private HttpEntity<ListDto<ProductTemplate>> getProduct(
            @ModelAttribute @Valid ProductSearchTemplate productSearchTemplate,
            HttpServletRequest request
//            @RequestParam(required = false) @Valid String key,
//            @RequestParam(required = false) @Valid CurrencyEnum currency,
//            @RequestParam(required = false) @Valid Long minPrice,
//            @RequestParam(required = false) @Valid Long maxPrice,
//            @RequestParam(required = false) @Valid Long minCreatedDate,
//            @RequestParam(required = false) @Valid Long maxCreatedDate,
//            @RequestParam(required = false) @Valid List<CategoryEnum> categories,
//            @RequestParam(required = false) @Valid Long count
    ) {

        log.info(productSearchTemplate.getTitle());

        int n = 20;

        if(productSearchTemplate.getCount() != null){
        if (productSearchTemplate.getCount().intValue() >= 0)
            n = productSearchTemplate.getCount().intValue();
        }

        ListDto<ProductTemplate> productListDto;

        if(productSearchTemplate.getCount().intValue()!=0) {
            List<ProductTemplate> productTemplateList = new ArrayList<>();
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

                List<CategoryEnum> categoryEnumList = new ArrayList<>();
                categoryEnumList.add(CategoryEnum.BORROW);
                categoryEnumList.add(CategoryEnum.SELL);
                categoryEnumList.add(CategoryEnum.BUY);

                productTemplate.setCategoryEnumList(categoryEnumList);
                productTemplate.setCreatedDate(new Date().getTime());
                productTemplate.setUpdatedDate(new Date().getTime());
                productTemplateList.add(productTemplate);
            }


            productListDto = new ListDto<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), productTemplateList);

        }else{
            List<ProductTemplate> productTemplateList = new ArrayList<>();
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

                List<CategoryEnum> categoryEnumList = new ArrayList<>();
                categoryEnumList.add(CategoryEnum.BORROW);

                productTemplate.setCategoryEnumList(categoryEnumList);
                productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
                productTemplate.setUpdatedDate(new Date().getTime());
                productTemplateList.add(productTemplate);
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

                List<CategoryEnum> categoryEnumList = new ArrayList<>();
                categoryEnumList.add(CategoryEnum.SELL);

                productTemplate.setCategoryEnumList(categoryEnumList);
                productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
                productTemplate.setUpdatedDate(new Date().getTime());
                productTemplateList.add(productTemplate);
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

                List<CategoryEnum> categoryEnumList = new ArrayList<>();
                categoryEnumList.add(CategoryEnum.BUY);

                productTemplate.setCategoryEnumList(categoryEnumList);
                productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
                productTemplate.setUpdatedDate(new Date().getTime());
                productTemplateList.add(productTemplate);
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


                List<CategoryEnum> categoryEnumList = new ArrayList<>();
                categoryEnumList.add(CategoryEnum.BORROW);
                categoryEnumList.add(CategoryEnum.SELL);

                productTemplate.setCategoryEnumList(categoryEnumList);
                productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
                productTemplate.setUpdatedDate(new Date().getTime());
                productTemplateList.add(productTemplate);
            }


            productListDto = new ListDto<>(
                    new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR, "OK"),
                    productTemplateList);
        }

        return new ResponseEntity<>(productListDto, HttpStatus.OK);
    }
//
//    @GetMapping(
////            path = "/all",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    private HttpEntity<ListDto<ProductTemplate>> getAllProduct() {
//        //query la bd prin product facade
//
//        //primesc modele
//
////        ProductBuilder productBuilder = new ProductBuilder();
////        productBuilder.getProductTemplateList(
////                querry.List();
////        )
//
//        List<ProductTemplate> productTemplateList = new ArrayList<>();
//        for (int i = 0; i < 5; ++i) {
//            ProductTemplate productTemplate = new ProductTemplate();
//            productTemplate.setId((long) i);
//            productTemplate.setTitle("title_" + i);
//            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
//            productTemplate.setCurrency(CurrencyEnum.EUR);
//            productTemplate.setPrice((long) (i * 100));
//            productTemplate.setBorrow(true);
//            productTemplate.setLimitDate(new Date().getTime() + i * 1000);
//            productTemplate.setUserName("vasea_" + i);
//
//            List<CategoryEnum> categoryEnumList = new ArrayList<>();
//            categoryEnumList.add(CategoryEnum.BORROW);
//
//            productTemplate.setCategoryEnumList(categoryEnumList);
//            productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
//            productTemplate.setUpdatedDate(new Date().getTime());
//            productTemplateList.add(productTemplate);
//        }
//        for (int i = 5; i < 10; ++i) {
//            ProductTemplate productTemplate = new ProductTemplate();
//            productTemplate.setId((long) i);
//            productTemplate.setTitle("title_" + i);
//            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
//            productTemplate.setCurrency(CurrencyEnum.EUR);
//            productTemplate.setPrice((long) (i * 100));
//            productTemplate.setBorrow(true);
//            productTemplate.setLimitDate(new Date().getTime() + i * 1000);
//            productTemplate.setUserName("vasea_" + i);
//
//            List<CategoryEnum> categoryEnumList = new ArrayList<>();
//            categoryEnumList.add(CategoryEnum.SELL);
//
//            productTemplate.setCategoryEnumList(categoryEnumList);
//            productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
//            productTemplate.setUpdatedDate(new Date().getTime());
//            productTemplateList.add(productTemplate);
//        }
//        for (int i = 10; i < 15; ++i) {
//            ProductTemplate productTemplate = new ProductTemplate();
//            productTemplate.setId((long) i);
//            productTemplate.setTitle("title_" + i);
//            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
//            productTemplate.setCurrency(CurrencyEnum.MDL);
//            productTemplate.setPrice((long) (i * 100));
//            productTemplate.setBorrow(false);
//            productTemplate.setLimitDate(new Date().getTime() + i * 1000);
//            productTemplate.setUserName("vasea_" + i);
//
//            List<CategoryEnum> categoryEnumList = new ArrayList<>();
//            categoryEnumList.add(CategoryEnum.BUY);
//
//            productTemplate.setCategoryEnumList(categoryEnumList);
//            productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
//            productTemplate.setUpdatedDate(new Date().getTime());
//            productTemplateList.add(productTemplate);
//        }
//        for (int i = 15; i < 20; ++i) {
//            ProductTemplate productTemplate = new ProductTemplate();
//            productTemplate.setId((long) i);
//            productTemplate.setTitle("title_" + i);
//            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
//            productTemplate.setCurrency(CurrencyEnum.USD);
//            productTemplate.setPrice((long) (i * 100));
//            productTemplate.setBorrow(false);
//            productTemplate.setLimitDate(new Date().getTime() + i * 1000);
//            productTemplate.setUserName("vasea_" + i);
//
//
//            List<CategoryEnum> categoryEnumList = new ArrayList<>();
//            categoryEnumList.add(CategoryEnum.BORROW);
//            categoryEnumList.add(CategoryEnum.SELL);
//
//            productTemplate.setCategoryEnumList(categoryEnumList);
//            productTemplate.setCreatedDate(new Date().getTime() + i * 1000);
//            productTemplate.setUpdatedDate(new Date().getTime());
//            productTemplateList.add(productTemplate);
//        }
//
//
//        ListDto<ProductTemplate> productListDto = new ListDto<ProductTemplate>(
//                new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR, "OK"),
//                productTemplateList);
//
//
//        return new ResponseEntity<>(productListDto, HttpStatus.OK);
//    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ServerResponseStatus> saveProduct(
            @ApiParam(value = "what??") @RequestBody @Valid ProductCreateClientRequest productCreateClientRequest, HttpServletRequest request
    ) {
        log.info(productCreateClientRequest.getTitle());
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }
}
