package com.winify.cvsi.db.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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
    @Size(min=1,max=30, message = "username.Length between 1-30")
    private String username;
    @Column(nullable = false)
    @Size(min=1, message = "email.Length min=1")
    @Email(message = "bad email address")
    private String email;
    @Column(nullable = false)
    @Size(min=9, message = "password.Length min = 9")
    private String password;
    @Column(nullable = false)
    @Size(min=9, max=25, message = "phone.Length between 9-20")
    private String phone;
    @Column
    @Size(min=1, max=30, message = "name.Length between 1-30")
    private String name;
    @Column
    @Size(min=1, max=30, message = "surname.Length between 1-30")
    private String surname;
    @Column(nullable = false)
    @Size(min=10, message = "hash.Length min=10")
    private String hash;
    @Column(name="request_created_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "incorrect date")
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
