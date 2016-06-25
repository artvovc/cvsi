package com.winify.cvsi.db.model;

import javax.persistence.*;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long firstUserId;
    @Column(nullable = false)
    private Long secondUserId;
    @Column(nullable = false)
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(Long firstUserId) {
        this.firstUserId = firstUserId;
    }

    public Long getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserId(Long secondUserId) {
        this.secondUserId = secondUserId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
