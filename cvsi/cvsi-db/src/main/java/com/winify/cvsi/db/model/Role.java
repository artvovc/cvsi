package com.winify.cvsi.db.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Artemie on 28.06.2016.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(20) default \"ROLE_GUEST\"")
    private String role;



    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
