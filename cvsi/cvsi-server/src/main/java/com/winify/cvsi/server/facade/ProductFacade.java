package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.dto.ProductDto;
import com.winify.cvsi.core.dto.builder.ProductBuilder;
import com.winify.cvsi.core.dto.templates.request.ProductSearchClientRequest;
import com.winify.cvsi.core.dto.templates.request.ProductUpdateClientRequest;
import com.winify.cvsi.core.dto.templates.response.ProductTemplateResponse;
import com.winify.cvsi.core.service.ProductService;
import com.winify.cvsi.db.model.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductFacade {
    private final ProductService productService;
    Logger log = Logger.getLogger(ProductFacade.class);

    @Autowired
    public ProductFacade(ProductService productService) {
        this.productService = productService;
    }

    public ProductDto getProductDtoById(Long id) {
        return new ProductBuilder().getProductDto(productService.getProductById(id));
    }

    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

    public Long saveProduct(Product prod) {
        return productService.saveProduct(prod);
    }

    public Set<ProductTemplateResponse> getMyProducts(Long userId) {
        return new ProductBuilder().getProductTemplates(productService.getMyProducts(userId));
    }

    public Set<ProductTemplateResponse> getProducts(ProductSearchClientRequest productSearchClientRequest) {
        return new ProductBuilder().getProductTemplates(productService.getProducts(productSearchClientRequest));
    }

    public Set<Product> getMyProductsToArchivate(Long userId) {
        return productService.getMyProducts(userId);
    }

    public void updateProductToArchivate(Product product){
        productService.updateProduct(new ProductBuilder().getProductToArchivate(product));
    }

    public Long updateProduct(Long userId, Long productId) {
        return productService.updateProduct(userId,productId);
    }

    public void updateProduct(Product product,ProductUpdateClientRequest productUpdateClientRequest) {
        productService.updateProduct(new ProductBuilder().getProduct(product,productUpdateClientRequest));
    }
}
