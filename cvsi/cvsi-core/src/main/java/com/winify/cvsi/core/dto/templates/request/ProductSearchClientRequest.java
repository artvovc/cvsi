package com.winify.cvsi.core.dto.templates.request;

import com.winify.cvsi.core.dto.templates.ProductSearchTemplate;

/**
 * Created by Artemie on 01.07.2016.
 */
//Clasa model pentru request de la client
public class ProductSearchClientRequest {
    private ProductSearchTemplate productSearchTemplate;
    private String token;

    public ProductSearchTemplate getProductSearchTemplate() {
        return productSearchTemplate;
    }

    public void setProductSearchTemplate(ProductSearchTemplate productSearchTemplate) {
        this.productSearchTemplate = productSearchTemplate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
