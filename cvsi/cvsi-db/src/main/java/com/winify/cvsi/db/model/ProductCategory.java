package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Artemie on 29.06.2016.
 */
@Entity(name = "ProductCategory")
public class ProductCategory implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "FK_product_id")
    )
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey(name = "FK_category_id")
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
