package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.templates.ProductTemplate;
import com.winify.cvsi.core.dto.templates.request.CreateProductClientRequest;
import com.winify.cvsi.db.model.Product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductBuilder {

    public Product getProduct(CreateProductClientRequest createProductClientRequest) {
        Product product = new Product();
        product.setTitle(createProductClientRequest.getTitle());
        product.setDescription(createProductClientRequest.getDescription());
        product.setPrice(createProductClientRequest.getPrice());
        product.setCurrency(createProductClientRequest.getCurrency());
        product.setBorrow(createProductClientRequest.getBorrow());
        product.setArchived(false);
        product.setCreatedDate(new Date(createProductClientRequest.getCreatedDate()));
        product.setLimitDate(createProductClientRequest.getLimitDate()==null?null:new Date(createProductClientRequest.getLimitDate()));
        product.setUpdatedDate(createProductClientRequest.getUpdatedDate()==null?null:new Date(createProductClientRequest.getUpdatedDate()));
        product.setCategories(createProductClientRequest.getCategories());
        return product;
    }

    public ProductTemplate getProductTemplate(Product product) {
        ProductTemplate productTemplate = new ProductTemplate();
        productTemplate.setId(product.getId());
        productTemplate.setTitle(product.getTitle());
        productTemplate.setDescription(product.getDescription());
        productTemplate.setCurrency(product.getCurrency());
        productTemplate.setPrice(product.getPrice());
        productTemplate.setBorrow(product.getBorrow());
        productTemplate.setLimitDate(product.getLimitDate().getTime());
        productTemplate.setCategories(product.getCategories());
        productTemplate.setCreatedDate(product.getCreatedDate().getTime());
        productTemplate.setUpdatedDate(product.getUpdatedDate().getTime());
        return productTemplate;
    }

    public Set<ProductTemplate> getProductTemplates(Set<Product> products) {
        Set<ProductTemplate> productTemplates = new HashSet<>();
        products.forEach((product)->{
            ProductTemplate productTemplate = new ProductTemplate();
            productTemplate.setId(product.getId());
            productTemplate.setTitle(product.getTitle());
            productTemplate.setDescription(product.getDescription());
            productTemplate.setCurrency(product.getCurrency());
            productTemplate.setPrice(product.getPrice());
            productTemplate.setBorrow(product.getBorrow());
            productTemplate.setLimitDate(product.getLimitDate()==null?null:product.getLimitDate().getTime());
            productTemplate.setCategories(product.getCategories());
            productTemplate.setCreatedDate(product.getCreatedDate().getTime());
            productTemplate.setUpdatedDate(product.getUpdatedDate()==null?null:product.getUpdatedDate().getTime());
            productTemplate.setUserName(product.getUser().getUsername());
            productTemplates.add(productTemplate);
        });
        return productTemplates;
    }
}
