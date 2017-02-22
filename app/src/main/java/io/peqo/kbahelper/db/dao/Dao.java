package io.peqo.kbahelper.db.dao;

import java.util.List;

public interface Dao<T, ID> {

    T findOne(ID id);
    boolean save(T entity);
    List<T> findAll();
    void delete(ID id);
    boolean exists(ID id);

}
