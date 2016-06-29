package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;

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
}
