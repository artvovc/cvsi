package com.winify.cvsi.db.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
public class Conversation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1)
    private Long id;
    @Column(nullable = false)
    @Min(value = 1)
    private Long productId;
    @Column(name="created_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date createdDate;
    @OneToMany(
            mappedBy = "conversation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Message> messageList = new ArrayList<Message>();
    @ManyToOne
    @JoinColumn(
            name = "user_id_conversation",
            foreignKey = @ForeignKey(name = "FK_user_id_conversation")
    )
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
