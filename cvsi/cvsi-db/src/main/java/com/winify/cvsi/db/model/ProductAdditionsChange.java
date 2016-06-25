package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
@Table(name = "product_additions_change")
public class ProductAdditionsChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }
}
