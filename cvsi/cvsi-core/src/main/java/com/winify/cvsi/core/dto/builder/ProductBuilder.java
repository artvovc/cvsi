package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.templates.ProductTemplate;
import com.winify.cvsi.db.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
public class ProductBuilder {

    public ProductTemplate getProductTemplate(Product product) {
        ProductTemplate productTemplate = new ProductTemplate();
        productTemplate.setId(product.getId());
        productTemplate.setTitle(product.getTitle());
        productTemplate.setDescription(product.getDescription());
        productTemplate.setCurrency(product.getCurrency());
        productTemplate.setPrice(product.getPrice());
        productTemplate.setBorrow(product.getIsBorrow());
        productTemplate.setLimitDate(product.getLimitDate());
        productTemplate.setCategoryEnumList(product.getCategoryEnumList());
        productTemplate.setCreatedDate(product.getCreatedDate());
        productTemplate.setUpdatedDate(product.getUpdatedDate());
        return productTemplate;
    }

    public List<ProductTemplate> getProductTemplateList(List<Product> productList) {
        List<ProductTemplate> productTemplateList = new ArrayList<ProductTemplate>();

        for (Product product : productList) {
            ProductTemplate productTemplate = new ProductTemplate();
            productTemplate.setId(product.getId());
            productTemplate.setTitle(product.getTitle());
            productTemplate.setDescription(product.getDescription());
            productTemplate.setCurrency(product.getCurrency());
            productTemplate.setPrice(product.getPrice());
            productTemplate.setBorrow(product.getIsBorrow());
            productTemplate.setLimitDate(product.getLimitDate());
            productTemplate.setCategoryEnumList(product.getCategoryEnumList());
            productTemplate.setCreatedDate(product.getCreatedDate());
            productTemplate.setUpdatedDate(product.getUpdatedDate());
            productTemplateList.add(productTemplate);
        }
        return productTemplateList;
    }
}
