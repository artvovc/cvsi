package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;

import java.util.Set;

public interface ProductDao extends CrudOperations<Product, Long> {
    Set<Product> getMyProducts(Long userId);

    Set<Product> getProducts(
            String title,
            Long count,
            Long offset,
            Long minPrice,
            Long maxPrice,
            Long minCreatedDate,
            Long maxCreatedDate,
            Boolean orderByPrice,
            Boolean orderByCreatedDate,
            CurrencyEnum currency,
            Set<CategoryEnum> categories
    );
}
