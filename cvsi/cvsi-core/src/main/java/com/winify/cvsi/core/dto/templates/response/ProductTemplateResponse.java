package com.winify.cvsi.core.dto.templates.response;

import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;
@ApiModel(value = "ProductTemplateResponse", description = "contain product minimal necessary information")
public class ProductTemplateResponse implements Serializable {
    @ApiModelProperty(name = "id", dataType = "Long",example = "0", notes = "contain product id")
    private Long id;
    @ApiModelProperty(name = "title", dataType = "String",example = "super product", notes = "contain product title")
    private String title;
    @ApiModelProperty(name = "description", dataType = "String",example = "my super product not used", notes = "contain product description")
    private String description;
    @ApiModelProperty(name = "currency", dataType = "CurrencyEnum",example = "'MDL' or 'USD' or 'EUR'", notes = "contain product currency")
    private CurrencyEnum currency;
    @ApiModelProperty(name = "price", dataType = "Long",example = "0", notes = "contain product price")
    private Long price;
    @ApiModelProperty(name = "isBorrow", dataType = "Boolean",example = "true", notes = "if product category types contain 'borrow' and product destination is land then value will be false")
    private Boolean isBorrow;
    @ApiModelProperty(name = "id", dataType = "Long",example = "0", notes = "contain product id")
    private Long limitDate;
    @ApiModelProperty(name = "categories", dataType = "Set<CategoryEnum>",example = "['BUY','BORROW']", notes = "contain product categories")
    private Set<CategoryEnum> categories;
    @ApiModelProperty(name = "username", dataType = "String",example = "SuperMan", notes = "contain product user name")
    private String userName;
    @ApiModelProperty(name = "createdDate", dataType = "Long",example = "0", notes = "contain product created date")
    private Long createdDate;
    @ApiModelProperty(name = "updatedDate", dataType = "Long",example = "0", notes = "contain product updated date")
    private Long updatedDate;

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

    public Long getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Long limitDate) {
        this.limitDate = limitDate;
    }

    public Set<CategoryEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEnum> categories) {
        this.categories = categories;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
