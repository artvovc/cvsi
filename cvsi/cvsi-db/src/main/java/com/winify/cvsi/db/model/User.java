package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 24.06.2016.
 */
@Entity
@Table(
        name = "user_information",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email",name = "UK_email"),
                @UniqueConstraint(columnNames = "username",name = "UK_username")
        })
public class User implements Serializable{
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Conversation> conversationList = new ArrayList<Conversation>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Product>  productList = new ArrayList<Product>();
    @Column(name = "role_enum_list")
    @ElementCollection(targetClass = RoleEnum.class)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "FK_user_id_role")))
    @Enumerated(EnumType.ORDINAL)
    private List<RoleEnum> roleEnumList = new ArrayList<RoleEnum>();

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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

    public List<RoleEnum> getRoleEnumList() {
        return roleEnumList;
    }

    public void setRoleEnumList(List<RoleEnum> roleEnumList) {
        this.roleEnumList = roleEnumList;
    }

}
