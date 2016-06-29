package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.service.ProductService;
import com.winify.cvsi.db.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Artemie on 28.06.2016.
 */
@Service
public class ProductFacade {
    @Autowired
    private ProductService productService;

    public void saveProduct(Product prod){
        productService.saveProduct(prod);
    }
}
