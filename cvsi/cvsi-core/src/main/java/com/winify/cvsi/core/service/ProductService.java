package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.ProductDao;
import com.winify.cvsi.db.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public Product getProductById(Long id){
        return productDao.findById(id);
    }
    @Transactional
    public Set<Product> getMyProducts(Long userId) { return productDao.getMyProducts(userId); }
    @Transactional
    public void saveProduct(Product prod) {
        productDao.save(prod);
    }

}
