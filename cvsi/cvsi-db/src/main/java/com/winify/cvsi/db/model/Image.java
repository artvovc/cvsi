package com.winify.cvsi.db.model;

import com.winify.cvsi.db.model.enums.ImageType;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(
        name = "image")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageType imageType;
    //http://www.codejava.net/frameworks/hibernate/hibernate-binary-data-and-blob-mapping-example
    @Column(name = "image")
    private Blob image;
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id_image",
            foreignKey = @ForeignKey(name = "FK_product_id_image")
    )
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
