package com.winify.cvsi.server.security;

import com.winify.cvsi.db.model.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Artemie on 27.06.2016.
 */
public class RoleGrantedAuthority implements GrantedAuthority {

    private RoleEnum name;

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
    public String getAuthority() {
        return name.toString();
    }
}
