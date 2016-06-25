package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.UserDao;
import com.winify.cvsi.db.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Artemie on 25.06.2016.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public User getUser(Long id){
        return userDao.findById(id);
    }
    @Transactional
    public void saveUser (User user){ userDao.save(user);}
}
