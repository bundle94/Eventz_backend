package com.eventz.io.eventz.dao;

import java.util.List;

/**
 * Created by Michael.Akobundu on 4/4/2019.
 */
public interface BaseDao<T> {
    public T create(T Model);

    public boolean update(T model);

    public T find(long id);

    public List<T> findAll();

    //public Page<T> findAll(int pageNumber, int pageSize);

    public boolean delete(T model);

    //public void clearCache();
}
