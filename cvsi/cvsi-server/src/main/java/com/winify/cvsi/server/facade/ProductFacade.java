package com.winify.cvsi.server.facade;

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

    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

    public Set<Product> getMyProducts(Long userId) {
        return productService.getMyProducts(userId);
    }

    public void saveProduct(Product prod) {
        productService.saveProduct(prod);
    }
}
