package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;

import java.util.Set;

public interface UserDao extends CrudOperations<User, Long> {
    User findByEmail(String email);
}
