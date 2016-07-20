package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.UserDao;
import com.winify.cvsi.db.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    public User findByEmail(String uName) {
        User user = this.getCurrentSession()
                .createQuery("select u from User u where u.email= :pEmail", clazz)
                .setParameter("pEmail", uName)
                .getSingleResult();
        return user;
    }
}
