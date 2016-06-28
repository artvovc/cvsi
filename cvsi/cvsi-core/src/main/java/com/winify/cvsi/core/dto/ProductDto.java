package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.builder.ProductBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Artemie on 28.06.2016.
 */
public class ProductDto extends CvsiResponse {

    private Long id;
    private String title;
    private String description;
    private Date postedDate;
    private String category;
    private BigDecimal price;
    private Date limitDate;
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public ProductDto(){

    }
    public ProductDto(CvsiResponse cvsiResponse, ProductBuilder productBuilder){
        super(cvsiResponse);
        this.id = productBuilder.getId();
        this.title= productBuilder.getTitle();
        this.description= productBuilder.getDescription();
        this.postedDate= productBuilder.getPostedDate();
        this.category= productBuilder.getCategory();
        this.price= productBuilder.getPrice();
        this.limitDate= productBuilder.getLimitDate();
        this.updateDate= productBuilder.getUpdateDate();
    }
    public ProductDto(ProductBuilder productBuilder){
        this.id = productBuilder.getId();
        this.title= productBuilder.getTitle();
        this.description= productBuilder.getDescription();
        this.postedDate= productBuilder.getPostedDate();
        this.category= productBuilder.getCategory();
        this.price= productBuilder.getPrice();
        this.limitDate= productBuilder.getLimitDate();
        this.updateDate= productBuilder.getUpdateDate();
    }

}
