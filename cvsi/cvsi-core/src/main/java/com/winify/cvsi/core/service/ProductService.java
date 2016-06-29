package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.ProductDao;
import com.winify.cvsi.db.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Artemie on 28.06.2016.
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Transactional
    public void saveProduct(Product prod){
        productDao.save(prod);
    }

}
