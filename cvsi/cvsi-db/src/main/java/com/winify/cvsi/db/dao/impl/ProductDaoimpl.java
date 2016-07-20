package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ProductDao;
import com.winify.cvsi.db.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ProductDaoimpl extends AbstractDao<Product, Long> implements ProductDao {
    public ProductDaoimpl() {
        super(Product.class);
    }

    public Set<Product> getMyProducts(Long userId){
        return new HashSet<>(this.getCurrentSession()
                .createQuery("SELECT p FROM Product AS p WHERE p.user.id = :userId",clazz)
                .setParameter("userId",userId)
                .list());
    }
}
