package com.winify.cvsi.server.security;

import com.winify.cvsi.db.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by Artemie on 27.06.2016.
 */
public class CustomUser implements UserDetails {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public CustomUser(){}
    public CustomUser(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getName();
        this.lastName = user.getSurname();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }
}
