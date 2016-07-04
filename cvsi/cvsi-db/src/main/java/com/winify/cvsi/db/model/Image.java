package com.winify.cvsi.db.model;



import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="image_type",nullable = false)
    @Size(min = 1, max = 20, message = "imgtype.Length between 1-20")
    private String imgType;
    //http://www.codejava.net/frameworks/hibernate/hibernate-binary-data-and-blob-mapping-example
    @Column(name = "image")
    private Blob img;
    @Column(name = "created_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date createdDate;

    @ManyToOne
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

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
