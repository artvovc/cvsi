package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Artemie on 25.06.2016.
 */
@Entity
@Table(
        name = "registration",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email",name = "UK_email_registration"),
                @UniqueConstraint(columnNames = "username",name = "UK_username_registration")
        }
)
public class Registration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(nullable = false)
    private String hash;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestCreatedDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getRequestCreatedDate() {
        return requestCreatedDate;
    }

    public void setRequestCreatedDate(Date requestCreatedDate) {
        this.requestCreatedDate = requestCreatedDate;
    }

}
