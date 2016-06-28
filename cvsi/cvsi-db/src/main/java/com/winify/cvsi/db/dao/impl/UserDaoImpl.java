package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.UserDao;
import com.winify.cvsi.db.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Artemie on 25.06.2016.
 */
@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    public User findByUserName(String uName) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email",uName));
        User user = (User)criteria.uniqueResult();
        return user;
    }
}
