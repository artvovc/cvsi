package com.winify.cvsi.core.dto.templates.request;

import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Artemie on 07.07.2016.
 */
public class ProductCreateClientRequest implements Serializable {
    private String title;
    private String description;
    private CurrencyEnum currency;
    private Long price;
    private Boolean isBorrow;
    private Long limitDate;
    private List<CategoryEnum> categoryEnumList;

    private Long createdDate;
    private Long updatedDate;

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

    public Long getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Long limitDate) {
        this.limitDate = limitDate;
    }

    public List<CategoryEnum> getCategoryEnumList() {
        return categoryEnumList;
    }

    public void setCategoryEnumList(List<CategoryEnum> categoryEnumList) {
        this.categoryEnumList = categoryEnumList;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }
}
