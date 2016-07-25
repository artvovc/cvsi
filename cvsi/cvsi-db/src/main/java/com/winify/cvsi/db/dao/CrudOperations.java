package com.winify.cvsi.db.dao;

public interface CrudOperations<T, I> {
    void save(T entity);
    T findById(I id);
    void update(T entity);
    void delete(T entity);
}
