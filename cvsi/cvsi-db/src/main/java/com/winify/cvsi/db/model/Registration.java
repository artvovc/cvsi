package com.winify.cvsi.db.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
@Table(name = "registration")
public class Registration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String hash;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requeestCreatedDate;
    @Column(nullable = false)
    //DEFAULT VALUE MAST BE FALSE
    private Boolean isConfirmed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return userName;
    }

    public void setEmail(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getRequeestCreatedDate() {
        return requeestCreatedDate;
    }

    public void setRequeestCreatedDate(Date requeestCreatedDate) {
        this.requeestCreatedDate = requeestCreatedDate;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }
}
