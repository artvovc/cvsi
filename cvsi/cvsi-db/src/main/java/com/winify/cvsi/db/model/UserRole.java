package com.winify.cvsi.db.model;

import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.io.Serializable;

/**
 * Created by Artemie on 29.06.2016.
 */
@Entity(name = "User_Role")
public class UserRole implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(
            name = "user_id_user_role",
            foreignKey = @ForeignKey(name = "FK_user_id_user_role")
    )
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(
            name = "role_id_user_role",
            foreignKey = @ForeignKey(name = "FK_role_id_user_role")
    )
    private Role role;

    public UserRole(){}
    public UserRole(User user, Role role){
        this.user = user;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
