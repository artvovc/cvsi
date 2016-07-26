package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.UserDao;
import com.winify.cvsi.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User getUser(Long id) {
        return userDao.findById(id);
    }

    @Transactional
    public Long saveUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    public User getUserByMail(String email) {
        return userDao.findByEmail(email);
    }

    @Transactional
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
