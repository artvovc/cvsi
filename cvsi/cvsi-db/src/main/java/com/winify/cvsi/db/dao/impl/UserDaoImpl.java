package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.UserDao;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    public User findByEmail(String email) {
        User user = this.getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE u.email = :pEmail", clazz)
                .setParameter("pEmail", email)
                .getSingleResult();
        return user;
    }
}
