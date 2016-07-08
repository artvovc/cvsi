package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.ProductDto;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.ProductSearchTemplate;
import com.winify.cvsi.core.dto.templates.ProductTemplate;
import com.winify.cvsi.core.dto.templates.request.ProductCreateClientRequest;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;
import com.winify.cvsi.server.facade.ProductFacade;
import io.swagger.annotations.Api;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
@Controller
@Api
@RequestMapping(name = "product controller",
        path = "/product",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    final static Logger log = Logger.getLogger(ProductController.class);

    @GetMapping(path = "/search")
    private HttpEntity<ListDto<ProductTemplate>> getSearchProduct(
            @RequestBody @Valid ProductSearchTemplate productSearchTemplate
    ) {

        Integer in = productSearchTemplate.getCount();

        int n = 20;
        if(in!=null)
        n = in;


        List<ProductTemplate> productTemplateList = new ArrayList<ProductTemplate>();
        for(int i=0;i<n;++i){
            ProductTemplate productTemplate = new ProductTemplate();
            productTemplate.setId(new Long(i));
            productTemplate.setTitle("title_"+i);
            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
            productTemplate.setCurrency(CurrencyEnum.EUR);
            productTemplate.setPrice(new Long(i*100));
            productTemplate.setBorrow(new Boolean(true));
            productTemplate.setLimitDate(new Date().getTime());

            List<CategoryEnum> categoryEnumList = new ArrayList<CategoryEnum>();
            categoryEnumList.add(CategoryEnum.BORROW);
            categoryEnumList.add(CategoryEnum.SELL);
            categoryEnumList.add(CategoryEnum.BUY);

            productTemplate.setCategoryEnumList(categoryEnumList);
            productTemplate.setCreatedDate(new Date().getTime());
            productTemplate.setUpdatedDate(new Date().getTime());
            productTemplateList.add(productTemplate);}


        ListDto<ProductTemplate> productListDto = new ListDto<ProductTemplate>(
                new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR,"OK"),
                productTemplateList);


        return new ResponseEntity(productListDto, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    private HttpEntity<ListDto<ProductTemplate>> getAllProduct() {
        //query la bd prin product facade

        //primesc modele

//        ProductBuilder productBuilder = new ProductBuilder();
//        productBuilder.getProductTemplateList(
//                querry.List();
//        )

        List<ProductTemplate> productTemplateList = new ArrayList<ProductTemplate>();
        for(int i=0;i<5;++i){
            ProductTemplate productTemplate = new ProductTemplate();
            productTemplate.setId(new Long(i));
            productTemplate.setTitle("title_"+i);
            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
            productTemplate.setCurrency(CurrencyEnum.EUR);
            productTemplate.setPrice(new Long(i*100));
            productTemplate.setBorrow(new Boolean(true));
            productTemplate.setLimitDate(new Date().getTime());


            List<CategoryEnum> categoryEnumList = new ArrayList<CategoryEnum>();
            categoryEnumList.add(CategoryEnum.BORROW);
//            categoryEnumList.add(CategoryEnum.SELL);
//            categoryEnumList.add(CategoryEnum.BUY);

            productTemplate.setCategoryEnumList(categoryEnumList);
            productTemplate.setCreatedDate(new Date().getTime());
            productTemplate.setUpdatedDate(new Date().getTime());
            productTemplateList.add(productTemplate);}
        for(int i=5;i<10;++i){
            ProductTemplate productTemplate = new ProductTemplate();
            productTemplate.setId(new Long(i));
            productTemplate.setTitle("title_"+i);
            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
            productTemplate.setCurrency(CurrencyEnum.EUR);
            productTemplate.setPrice(new Long(i*100));
            productTemplate.setBorrow(new Boolean(true));
            productTemplate.setLimitDate(new Date().getTime());


            List<CategoryEnum> categoryEnumList = new ArrayList<CategoryEnum>();
//            categoryEnumList.add(CategoryEnum.BORROW);
            categoryEnumList.add(CategoryEnum.SELL);
//            categoryEnumList.add(CategoryEnum.BUY);

            productTemplate.setCategoryEnumList(categoryEnumList);
            productTemplate.setCreatedDate(new Date().getTime());
            productTemplate.setUpdatedDate(new Date().getTime());
            productTemplateList.add(productTemplate);}
        for(int i=10;i<15;++i){
            ProductTemplate productTemplate = new ProductTemplate();
            productTemplate.setId(new Long(i));
            productTemplate.setTitle("title_"+i);
            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
            productTemplate.setCurrency(CurrencyEnum.EUR);
            productTemplate.setPrice(new Long(i*100));
            productTemplate.setBorrow(new Boolean(true));
            productTemplate.setLimitDate(new Date().getTime());


            List<CategoryEnum> categoryEnumList = new ArrayList<CategoryEnum>();
            categoryEnumList.add(CategoryEnum.BUY);

            productTemplate.setCategoryEnumList(categoryEnumList);
            productTemplate.setCreatedDate(new Date().getTime());
            productTemplate.setUpdatedDate(new Date().getTime());
            productTemplateList.add(productTemplate);}
        for(int i=15;i<20;++i){
            ProductTemplate productTemplate = new ProductTemplate();
            productTemplate.setId(new Long(i));
            productTemplate.setTitle("title_"+i);
            productTemplate.setDescription("long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text long long long text .");
            productTemplate.setCurrency(CurrencyEnum.EUR);
            productTemplate.setPrice(new Long(i*100));
            productTemplate.setBorrow(new Boolean(true));
            productTemplate.setLimitDate(new Date().getTime());


            List<CategoryEnum> categoryEnumList = new ArrayList<CategoryEnum>();
            categoryEnumList.add(CategoryEnum.BORROW);
            categoryEnumList.add(CategoryEnum.SELL);
//            categoryEnumList.add(CategoryEnum.BUY);

            productTemplate.setCategoryEnumList(categoryEnumList);
            productTemplate.setCreatedDate(new Date().getTime());
            productTemplate.setUpdatedDate(new Date().getTime());
            productTemplateList.add(productTemplate);}


        ListDto<ProductTemplate> productListDto = new ListDto<ProductTemplate>(
                new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR,"OK"),
                productTemplateList);


        return new ResponseEntity<ListDto<ProductTemplate>>(productListDto, HttpStatus.OK);
    }

    @PostMapping(
            path = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ServerResponseStatus> saveNewUser(
            @RequestBody @Valid ProductCreateClientRequest a, HttpServletRequest request
    ){

        log.info(a.getTitle());



        return new ResponseEntity<ServerResponseStatus>(new ServerResponseStatus(ErrorEnum.SUCCESS,"OK"), HttpStatus.OK);
    }
}
