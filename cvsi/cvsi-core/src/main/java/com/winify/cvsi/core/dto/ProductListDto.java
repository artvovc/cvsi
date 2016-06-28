package com.winify.cvsi.core.dto;


import com.winify.cvsi.core.dto.builder.ProductBuilder;
import com.winify.cvsi.core.enums.ErrorEnum;

import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
public class ProductListDto extends CvsiResponse {

    private List<ProductBuilder> productBuilderList;

    public ProductListDto(List<ProductBuilder> productBuilderList) {
        this.productBuilderList = productBuilderList;
    }

    public ProductListDto(ErrorEnum error, String status, List<ProductBuilder> productBuilderList) {
        super(error, status);
        this.productBuilderList = productBuilderList;
    }

    public ProductListDto(CvsiResponse cvsiResponse, List<ProductBuilder> productBuilderList) {
        super(cvsiResponse);
        this.productBuilderList = productBuilderList;
    }

    public List<ProductBuilder> getProductBuilderList() {
        return productBuilderList;
    }

    public void setProductBuilderList(List<ProductBuilder> productBuilderList) {
        this.productBuilderList = productBuilderList;
    }
}
