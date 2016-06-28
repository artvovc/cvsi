package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Artemie on 25.06.2016.
 */

// WILL BE LOCAL STORAGE

@Entity
@Table(name = "image")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String imgType;
    //@Column(nullable = false)
    //private Image img;

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
}
