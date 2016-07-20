package com.winify.cvsi.db.model;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(
        name = "conversation")
public class Conversation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date createdDate;
    @OneToMany(
            mappedBy = "conversation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<Message> messages = new HashSet<>();
    @ManyToOne
    @JoinColumn(
            name = "user_id_conversation",
            foreignKey = @ForeignKey(name = "FK_user_id_conversation")
    )
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id_conversation",
            foreignKey = @ForeignKey(name = "FK_product_id_conversation")
    )
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
