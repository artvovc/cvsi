package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Artemie on 29.06.2016.
 */
@Entity(name = "Product_Category")
public class ProductCategory implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(
            name = "product_id_product_category",
            foreignKey = @ForeignKey(name = "FK_product_id_product_category")
    )
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(
            name = "category_id_product_category",
            foreignKey = @ForeignKey(name = "FK_category_id_product_category")
    )
    private Category category;

    public ProductCategory(){}

    public ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
