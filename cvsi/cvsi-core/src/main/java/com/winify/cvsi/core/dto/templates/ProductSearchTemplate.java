package com.winify.cvsi.core.dto.templates;

import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 01.07.2016.
 */
//Clasa template pentru formarea requestului de la un client
public class ProductSearchTemplate {
    private Long id;
    private String title;
    private CurrencyEnum currency;
    private Long minPrice;
    private Long maxPrice;
    private List<CategoryEnum> categoryEnumList;
    private Date minCreatedDate;
    private Date maxCreatedDate;

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

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<CategoryEnum> getCategoryEnumList() {
        return categoryEnumList;
    }

    public void setCategoryEnumList(List<CategoryEnum> categoryEnumList) {
        this.categoryEnumList = categoryEnumList;
    }

    public Date getMinCreatedDate() {
        return minCreatedDate;
    }

    public void setMinCreatedDate(Date minCreatedDate) {
        this.minCreatedDate = minCreatedDate;
    }

    public Date getMaxCreatedDate() {
        return maxCreatedDate;
    }

    public void setMaxCreatedDate(Date maxCreatedDate) {
        this.maxCreatedDate = maxCreatedDate;
    }
}
