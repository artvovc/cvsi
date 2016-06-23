package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.CrudOperations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Artemie on 22.06.2016.
 */
public abstract class AbstractDao<T, I> implements CrudOperations<T, I>{
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> clazz;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void save(T entity){
        getCurrentSession().save(entity);

    }

    public T findById(I identifier){
        return getCurrentSession().find(clazz, identifier);

    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
