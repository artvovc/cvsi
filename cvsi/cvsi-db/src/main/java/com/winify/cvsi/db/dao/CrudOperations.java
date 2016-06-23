package com.winify.cvsi.db.dao;

/**
 * Created by Artemie on 22.06.2016.
 */
public interface CrudOperations<T, I> {
   void save(T entity);

    T findById(I id);
}
