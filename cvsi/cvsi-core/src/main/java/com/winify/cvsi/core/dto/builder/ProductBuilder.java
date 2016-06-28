package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ProductDto;
import com.winify.cvsi.db.model.Product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Artemie on 28.06.2016.
 */
// DE SCHIMBAT, NU-I CORECT
public class ProductBuilder {

    private Long id;
    private String title;
    private String description;
    private Date postedDate;
    private String category;
    private BigDecimal price;
    private Date limitDate;
    private Date updateDate;

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public ProductBuilder(){}

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

    public ProductBuilder(Product product) {
        this.id = product.getId();
        this.title= product.getTitle();
        this.description= product.getDescription();
        this.postedDate= product.getPostedDate();
        this.category= product.getCategory();
        this.price= product.getPrice();
        this.limitDate= product.getLimitDate();
        this.updateDate= product.getUpdateDate();
    }

}
