package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.ProductDto;
import com.winify.cvsi.core.dto.templates.response.ProductTemplateResponse;
import com.winify.cvsi.core.dto.templates.request.ProductCreateClientRequest;
import com.winify.cvsi.db.model.Product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductBuilder {

    public Product getProduct(ProductCreateClientRequest productCreateClientRequest) {
        Product product = new Product();
        product.setTitle(productCreateClientRequest.getTitle());
        product.setDescription(productCreateClientRequest.getDescription());
        product.setPrice(productCreateClientRequest.getPrice());
        product.setCurrency(productCreateClientRequest.getCurrency());
        product.setBorrow(productCreateClientRequest.getBorrow());
        product.setArchived(false);
        product.setCreatedDate(new Date(productCreateClientRequest.getCreatedDate()));
        product.setLimitDate(productCreateClientRequest.getLimitDate()==null?null:new Date(productCreateClientRequest.getLimitDate()));
        product.setUpdatedDate(productCreateClientRequest.getUpdatedDate()==null?null:new Date(productCreateClientRequest.getUpdatedDate()));
        product.setCategories(productCreateClientRequest.getCategories());
        return product;
    }

    public ProductTemplateResponse getProductTemplate(Product product) {
        ProductTemplateResponse productTemplateResponse = new ProductTemplateResponse();
        productTemplateResponse.setId(product.getId());
        productTemplateResponse.setTitle(product.getTitle());
        productTemplateResponse.setDescription(product.getDescription());
        productTemplateResponse.setCurrency(product.getCurrency());
        productTemplateResponse.setPrice(product.getPrice());
        productTemplateResponse.setBorrow(product.getBorrow());
        productTemplateResponse.setLimitDate(product.getLimitDate()==null?null:product.getLimitDate().getTime());
        productTemplateResponse.setCategories(product.getCategories());
        productTemplateResponse.setCreatedDate(product.getCreatedDate().getTime());
        productTemplateResponse.setUpdatedDate(product.getUpdatedDate()==null?null:product.getUpdatedDate().getTime());
        return productTemplateResponse;
    }

    public Set<ProductTemplateResponse> getProductTemplates(Set<Product> products) {
        Set<ProductTemplateResponse> productTemplateResponses = new HashSet<>();
        products.forEach((product)->{
            ProductTemplateResponse productTemplateResponse = new ProductTemplateResponse();
            productTemplateResponse.setId(product.getId());
            productTemplateResponse.setTitle(product.getTitle());
            productTemplateResponse.setDescription(product.getDescription());
            productTemplateResponse.setCurrency(product.getCurrency());
            productTemplateResponse.setPrice(product.getPrice());
            productTemplateResponse.setBorrow(product.getBorrow());
            productTemplateResponse.setLimitDate(product.getLimitDate()==null?null:product.getLimitDate().getTime());
            productTemplateResponse.setCategories(product.getCategories());
            productTemplateResponse.setCreatedDate(product.getCreatedDate().getTime());
            productTemplateResponse.setUpdatedDate(product.getUpdatedDate()==null?null:product.getUpdatedDate().getTime());
            productTemplateResponse.setUserName(product.getUser().getUsername());
            productTemplateResponses.add(productTemplateResponse);
        });
        return productTemplateResponses;
    }
    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProductTemplateResponse(this.getProductTemplate(product));
        return productDto;
    }
}