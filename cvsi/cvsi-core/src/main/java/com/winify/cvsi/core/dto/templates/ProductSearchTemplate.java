package com.winify.cvsi.core.dto.templates;

import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@ApiModel
public class ProductSearchTemplate implements Serializable {
    private String title;
    private CurrencyEnum currency;
    private Long minPrice;
    private Long maxPrice;
    private Set<CategoryEnum> categories;
    private Long minCreatedDate;
    private Long maxCreatedDate;
    private Long offset;
    private Long count;
    private Boolean myProducts;
    private Boolean orderByPrice;
    private Boolean orderByCreatedDate;

    public ProductSearchTemplate() {
        this.minPrice = null;//0L;
        this.maxPrice = Long.MAX_VALUE;
        this.minCreatedDate = new Date().getTime() - (1000L*60*60*24*30);// 30 days earlier
        this.maxCreatedDate = new Date().getTime();
        this.offset = 0L;
        this.count = 50L;
        this.myProducts = false;
        this.orderByPrice = false;
        this.orderByCreatedDate = false;
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

    public Set<CategoryEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEnum> categories) {
        this.categories = categories;
    }

    public Long getMinCreatedDate() {
        return minCreatedDate;
    }

    public void setMinCreatedDate(Long minCreatedDate) {
        this.minCreatedDate = minCreatedDate;
    }

    public Long getMaxCreatedDate() {
        return maxCreatedDate;
    }

    public void setMaxCreatedDate(Long maxCreatedDate) {
        this.maxCreatedDate = maxCreatedDate;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Boolean getMyProducts() {
        return myProducts;
    }

    public void setMyProducts(Boolean myProducts) {
        this.myProducts = myProducts;
    }

    public Boolean getOrderByPrice() {
        return orderByPrice;
    }

    public void setOrderByPrice(Boolean orderByPrice) {
        this.orderByPrice = orderByPrice;
    }

    public Boolean getOrderByCreatedDate() {
        return orderByCreatedDate;
    }

    public void setOrderByCreatedDate(Boolean orderByCreatedDate) {
        this.orderByCreatedDate = orderByCreatedDate;
    }
}
