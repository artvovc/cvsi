package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Artemie on 28.06.2016.
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

}
