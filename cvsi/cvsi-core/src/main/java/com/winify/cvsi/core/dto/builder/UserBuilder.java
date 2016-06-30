package com.winify.cvsi.core.dto.builder;

import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.db.model.User;

/**
 * Created by Artemie on 28.06.2016.
 */
public class UserBuilder {
    private String userName;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public UserBuilder(){

    }
    public UserBuilder(User user){
        this.userName = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    public UserBuilder(UserDto userDto){
        this.userName = userDto.getUserName();
        this.name = userDto.getName();
        this.surname = userDto.getSurname();
        this.phone = userDto.getPhone();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }

    public UserDto getUserDto(){
        return new UserDto(this);
    }
}
