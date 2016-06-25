package com.winify.cvsi.db.model;

import javax.persistence.*;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
@Table(name = "product_additions_borrow")
public class ProductAdditionsBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String itemForChange;
    @Column
    private String itemNeeded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemForChange() {
        return itemForChange;
    }

    public void setItemForChange(String itemForChange) {
        this.itemForChange = itemForChange;
    }

    public String getItemNeeded() {
        return itemNeeded;
    }

    public void setItemNeeded(String itemNeeded) {
        this.itemNeeded = itemNeeded;
    }
}
