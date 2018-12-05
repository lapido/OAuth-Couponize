package com.couponize.Auth.dao;

import com.couponize.Auth.model.Page;

import java.util.List;

public interface BaseDao<T> {
    public T create (T model);

    public  boolean update (T model);

    public T find(long id);

    public List<T> findAll();

    public boolean delete (T model);

    public Page<T> findAll(int pageNumber, int pageSize);

}
