package com.winify.cvsi.core.dto.templates;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 01.07.2016.
 */
// Clasa template pentru un item din lista de producte
public class ProductTemplate {
    private Long id;
    private String title;
    private String description;
    private CurrencyEnum currency;
    private Long price;
    private Boolean isBorrow;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private Date limitDate;
    private List<CategoryEnum> categoryEnumList;

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private Date createdDate;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private Date updatedDate;

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

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getBorrow() {
        return isBorrow;
    }

    public void setBorrow(Boolean borrow) {
        isBorrow = borrow;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public List<CategoryEnum> getCategoryEnumList() {
        return categoryEnumList;
    }

    public void setCategoryEnumList(List<CategoryEnum> categoryEnumList) {
        this.categoryEnumList = categoryEnumList;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
