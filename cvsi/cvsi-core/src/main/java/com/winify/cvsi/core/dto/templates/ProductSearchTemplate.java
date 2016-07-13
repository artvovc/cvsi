package com.winify.cvsi.core.dto.templates;

import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Artemie on 01.07.2016.
 */
@ApiModel
public class ProductSearchTemplate implements Serializable {
    private String title;
    private CurrencyEnum currency;
    private Long minPrice;
    private Long maxPrice;
    private List<CategoryEnum> categoryEnumList;
    private Long minCreatedDate;
    private Long maxCreatedDate;
    private Long count = 50L;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
