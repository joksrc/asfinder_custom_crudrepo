package com.asfinder.api.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository <T, ID extends Serializable>{

    T save(T t);

    boolean delete (Object id);

    T update(T t);

    T findById(Integer id);

    List<T> findAll();

}
