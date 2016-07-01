package com.winify.cvsi.core.dto;


import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.ProductTemplate;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
public class ProductListDto extends ServerResponseStatus {

    private List<ProductTemplate> productList;

    public ProductListDto(){productList= new ArrayList<ProductTemplate>();}

    public ProductListDto(List<ProductTemplate> productList) {
        this.productList = productList;
    }

    public ProductListDto(ErrorEnum error, String status, List<ProductTemplate> productList) {
        super(error, status);
        this.productList = productList;
    }

    public ProductListDto(ServerResponseStatus serverResponseStatus, List<ProductTemplate> productList) {
        super(serverResponseStatus);
        this.productList = productList;
    }

    public List<ProductTemplate> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductTemplate> productList) {
        this.productList = productList;
    }
}
