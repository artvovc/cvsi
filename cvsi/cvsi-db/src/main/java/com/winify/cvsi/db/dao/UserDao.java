package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.User;

public interface UserDao extends CrudOperations<User, Long> {
    User findByEmail(String uName);
}
