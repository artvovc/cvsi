package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.User;

/**
 * Created by Artemie on 25.06.2016.
 */
public interface UserDao extends CrudOperations<User,Long> {
    User findByUsername(String uname);
}
