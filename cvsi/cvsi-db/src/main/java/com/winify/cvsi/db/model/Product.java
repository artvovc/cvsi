package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date",nullable = false)
    private Date createdDate;
    @Column
    private Long price;
    @Column(name="limit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitDate;
    @Column(name="updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "category_enum_list")
    @ElementCollection(targetClass = CategoryEnum.class)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    foreignKey = @ForeignKey(
                            name = "FK_product_id_category")))
    @Enumerated(EnumType.ORDINAL)
    private List<CategoryEnum> categoryEnumList = new ArrayList<CategoryEnum>();

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Image> imageList = new ArrayList<Image>();

    @ManyToOne
    @JoinColumn(
            name = "user_id_product",
            foreignKey = @ForeignKey(name = "FK_user_id_product")
    )
    private User user;

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<CategoryEnum> getCategoryEnumList() {
        return categoryEnumList;
    }

    public void setCategoryEnumList(List<CategoryEnum> categoryEnumList) {
        this.categoryEnumList = categoryEnumList;
    }
}
