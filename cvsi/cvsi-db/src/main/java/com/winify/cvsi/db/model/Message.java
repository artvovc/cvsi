package com.winify.cvsi.db.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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
    @Min(value = 1)
    private Long id;
    @Column(nullable = false)
    @Size(min=1, message = "message.Length min=1")
    private String message;
    @Column(name="created_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date createdDate;
    @Column(name="updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
    private Date updatedDate;
    @Column(name = "is_read",nullable = false,columnDefinition = "bit default 0")
    @AssertFalse(message = "message must be not read")
//    @Type(type= "org.hibernate.type.NumericBooleanType")   nu vra padla
    private boolean isRead;
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

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
