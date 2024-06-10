package com.alonso.abm.dao;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface BasicDAO<T> {

    public T save(T obj);

    public void delete(Long id);

    public List<T> getAll();

    public T getById(Long id);


}
