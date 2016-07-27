package com.winify.cvsi.core.service;

import com.winify.cvsi.core.dto.templates.request.ProductSearchClientRequest;
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
    public Product getProductById(Long id) {
        return productDao.findById(id);
    }

    @Transactional
    public Long saveProduct(Product prod) {
        return productDao.save(prod);
    }

    @Transactional
    public Set<Product> getMyProducts(Long userId) {
        return productDao.getMyProducts(userId);
    }

    @Transactional
    public Set<Product> getProducts(ProductSearchClientRequest productSearchClientRequest) {
        return productDao.getProducts(
                productSearchClientRequest.getTitle(),
                productSearchClientRequest.getCount(),
                productSearchClientRequest.getOffset(),
                productSearchClientRequest.getMinPrice(),
                productSearchClientRequest.getMaxPrice(),
                productSearchClientRequest.getMinCreatedDate(),
                productSearchClientRequest.getMaxCreatedDate(),
                productSearchClientRequest.getOrderByPrice(),
                productSearchClientRequest.getOrderByCreatedDate(),
                productSearchClientRequest.getCurrency(),
                productSearchClientRequest.getCategories()
        );
    }

    @Transactional
    public void updateProduct(Product product){
        productDao.update(product);
    }

    @Transactional
    public Long updateProduct(Long userId, Long productId) {
        return productDao.updateProductByUserIdAndProductId(userId, productId);
    }
}
