package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ProductDao;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
public class ProductDaoimpl extends AbstractDao<Product, Long> implements ProductDao {
    public ProductDaoimpl() {
        super(Product.class);
    }

    public Set<Product> getMyProducts(Long userId) {
        Set<Product> products = new HashSet<>(this.getCurrentSession()
                .createQuery("SELECT p FROM Product AS p WHERE p.user.id = :userId", clazz)
                .setParameter("userId", userId)
                .getResultList());
        this.setProductCategories(products);
        this.setProductUser(products);
        return products;
    }

    public Set<Product> getProducts(
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
            Set<CategoryEnum> categories) {
        String queryBuild = "SELECT p.* " +
                "FROM product AS p ";
        queryBuild = addPriceQuery(queryBuild);
        queryBuild = addCreatedDateQuery(queryBuild);
        queryBuild = addCurrencyQuery(currency, queryBuild);
        queryBuild = addCategoriesQuery(categories, queryBuild);
        queryBuild = addTitleQuery(title, queryBuild);
        queryBuild = addOrderByQuery(orderByPrice, orderByCreatedDate, queryBuild);
        Query<Product> query = this.getCurrentSession().createNativeQuery(queryBuild, clazz);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        query.setParameter("minCreatedDate", minCreatedDate);
        query.setParameter("maxCreatedDate", maxCreatedDate);
        if (currency != null)
            query.setParameter("currencyEnum", currency.name());
        query.setFirstResult(offset.intValue());
        query.setMaxResults(count.intValue());
        Set<Product> products = new HashSet<>(query.getResultList());
        this.setProductCategories(products);
        this.setProductUser(products);
        return products;
    }

    private void setProductCategories(Set<Product> products) {
        products.forEach((product) -> product.setCategories(
                this.convertCategoriesFromStringToCategoryEnum(
                        this.getCurrentSession()
                                .createNativeQuery("SELECT pc.category_enum_set " +
                                        "FROM product_category AS pc " +
                                        "WHERE pc.product_id = :productId")
                                .setParameter("productId", product.getId())
                                .getResultList()
                )
        ));
    }

    private Set<CategoryEnum> convertCategoriesFromStringToCategoryEnum(List<String> categoriesList) {
        Set<CategoryEnum> categories = new HashSet<>();
        categoriesList.forEach((category) -> {
            for (CategoryEnum categoryEnum : CategoryEnum.values()) {
                if (Objects.equals(categoryEnum.toString(), category))
                    categories.add(categoryEnum);
            }
        });
        return categories;
    }

    private String addPriceQuery(String queryBuild) {
        queryBuild += "WHERE " +
                "(p.price BETWEEN :minPrice AND :maxPrice) ";
        return queryBuild;
    }

    private String addCreatedDateQuery(String queryBuild) {
        queryBuild += "AND " +
                "(UNIX_TIMESTAMP(p.created_date) * 1000 BETWEEN :minCreatedDate AND :maxCreatedDate) ";
        return queryBuild;
    }

    private String addCurrencyQuery(CurrencyEnum currency, String queryBuild) {
        if (currency != null) {
            queryBuild += "AND " +
                    "(p.currency = :currencyEnum) ";
        }
        return queryBuild;
    }

    private String addCategoriesQuery(Set<CategoryEnum> categories, String queryBuild) {
        if (categories != null) {
            queryBuild += "AND " +
                    "(p.id IN (SELECT pc.product_id FROM product_category AS pc WHERE p.id = pc.product_id AND pc.category_enum_set IN (";
            int commas = categories.size();
            --commas;
            for (CategoryEnum category : categories) {
                queryBuild += "'" + category.name() + "'";
                if (commas != 0) {
                    queryBuild += ",";
                    --commas;
                }
            }
            queryBuild += ")))";
        }
        return queryBuild;
    }

    private String addTitleQuery(String title, String queryBuild) {
        if (title != null) {
            queryBuild += "AND ";

            String[] keys = title.split(" ");
            int commas = keys.length;
            --commas;
            for (String key : keys) {
                queryBuild += "(p.title LIKE '%" + key + "%') ";
                if (commas != 0) {
                    queryBuild += "OR ";
                    --commas;
                }
            }
            queryBuild += "OR (p.title LIKE '&" + title + "&') ";
        }
        return queryBuild;
    }

    private String addOrderByQuery(Boolean orderByPrice, Boolean orderByCreatedDate, String queryBuild) {
        if (orderByPrice || orderByCreatedDate) {
            queryBuild += "ORDER BY ";
            if (orderByPrice) {
                queryBuild += "p.price ASC ";
                if (orderByCreatedDate) {
                    queryBuild += ", p.created_date ASC";
                }
            } else {
                queryBuild += "p.created_date ASC";
            }
        }
        return queryBuild;
    }

    private void setProductUser(Set<Product> products) {

        String query = "SELECT u " +
                "FROM User AS u WHERE u.id = (SELECT p.user.id FROM Product AS p WHERE p.id = :productId)";
        products.forEach(product -> product.setUser(
                this.getCurrentSession().createQuery(query, com.winify.cvsi.db.model.User.class).setParameter("productId", product.getId()).getSingleResult()
        ));
    }
}
