package com.winify.cvsi.server.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Artemie on 27.06.2016.
 */
public class RoleGrantedAuthority implements GrantedAuthority {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAuthority() {
        return name;
    }
}
