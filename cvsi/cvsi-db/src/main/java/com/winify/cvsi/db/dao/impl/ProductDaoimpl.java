package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ProductDao;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.User;
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

        String queryBuild = "SELECT p " +
                "FROM Product AS p";

        Query<Product> query = this.getCurrentSession().createQuery(queryBuild, clazz);

        if (false) {
            //LIMIT offset,count
            query.setFirstResult(offset.intValue());
            query.setMaxResults(count.intValue());
        }

        Set<Product> products = new HashSet<>(query.getResultList());
        this.setProductCategories(products);
        this.setProductUser(products);
        return products;
    }

    private void setProductUser(Set<Product> products) {

        String query = "SELECT u " +
                "FROM User AS u WHERE u.id = (SELECT p.user.id FROM Product AS p WHERE p.id = :productId)";
        User user = this.getCurrentSession().createQuery(query, User.class).setParameter("productId", 1L).getSingleResult();
        products.forEach(product -> {
            product.setUser(
                    this.getCurrentSession().createQuery(query, User.class).setParameter("productId", product.getId()).getSingleResult()
            );
        });
    }
}
