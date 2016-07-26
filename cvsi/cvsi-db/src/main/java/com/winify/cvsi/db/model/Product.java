package com.winify.cvsi.db.model;


import com.winify.cvsi.db.model.enums.CategoryEnum;
import com.winify.cvsi.db.model.enums.CurrencyEnum;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 1, max = 100, message = "title.Length between 1-100")
    private String title;
    @Column
    private String description;
    @Column
    @Digits(integer = 13, fraction = 2)
    private Long price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;
    @Column(name = "borrow_or_lend")
    private Boolean isBorrow;
    @Column(name = "is_archived")
    private Boolean isArchived;
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date createdDate;
    @Column(name = "limit_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Future(message = "incorrect date")
    private Date limitDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date updatedDate;

    @Column(name = "category_enum_set")
    @ElementCollection(
            targetClass = CategoryEnum.class,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    foreignKey = @ForeignKey(
                            name = "FK_product_id_category")))
    @Enumerated(EnumType.STRING)
    @Size(min = 1, message = "Set category for product")
    private Set<CategoryEnum> categories = new HashSet<>();

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<Image> images = new HashSet<>();

    @ManyToOne(
            fetch = FetchType.EAGER,
            targetEntity = User.class)
    @JoinColumn(
            name = "user_id_product",
            foreignKey = @ForeignKey(
                    name = "FK_user_id_product")
    )
    private User user;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public Boolean getBorrow() {
        return isBorrow;
    }

    public void setBorrow(Boolean borrow) {
        isBorrow = borrow;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<CategoryEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEnum> categories) {
        this.categories = categories;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
