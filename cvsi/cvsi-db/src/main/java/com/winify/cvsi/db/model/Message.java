package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String message;
    @Column(name="created_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name="updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(
            name = "conversation_id",
            foreignKey = @ForeignKey(name = "FK_conversation_id")
    )
    private Conversation conversation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
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
}
