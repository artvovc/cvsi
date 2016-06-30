package com.winify.cvsi.db.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Blob;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1)
    private Long id;
    @Column(nullable = false)
    @Size(min = 1, max = 20, message = "imgtype.Length between 1-20")
    private String imgType;
    //http://www.codejava.net/frameworks/hibernate/hibernate-binary-data-and-blob-mapping-example
    @Column
    private Blob img;

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
}
