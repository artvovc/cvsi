package com.winify.cvsi.db.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min=1,max=100, message = "title.Length between 1-100")
    private String title;
    @Column
    private String description;
    @Column
    @Digits(integer = 13,fraction = 2)
    private Long price;
    @Column
    private CurrencyEnum currency;
    @Column(name = "borrow_or_lend")
    private Boolean isBorrow;
    @Column(name="created_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date createdDate;
    @Column(name="limit_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Future(message = "incorrect date")
    private Date limitDate;
    @Column(name="updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date updatedDate;

    @Column(name = "category_enum_list")
    @ElementCollection(targetClass = CategoryEnum.class)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    foreignKey = @ForeignKey(
                            name = "FK_product_id_category")))
    @Enumerated(EnumType.STRING)
    @Size(min=1, message = "Set category for product")
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

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public Boolean getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(Boolean isBorrow) {
        this.isBorrow = isBorrow;
    }
}
