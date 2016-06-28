package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ProductDao;
import com.winify.cvsi.db.model.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by Artemie on 28.06.2016.
 */
@Repository
public class ProductDaoimpl extends AbstractDao<Product,Long> implements ProductDao {
    public ProductDaoimpl() {
        super(Product.class);
    }
}
