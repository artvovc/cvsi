package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.Product;

import java.util.Set;

public interface ProductDao extends CrudOperations<Product, Long> {
    public Set<Product> getMyProducts(Long userId);
}
