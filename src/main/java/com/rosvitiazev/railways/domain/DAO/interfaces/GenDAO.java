package com.rosvitiazev.railways.domain.DAO.interfaces;

import java.io.Serializable;
import java.util.List;

public interface GenDAO<T extends Serializable> {

    T save(T entity);

    List<T> findAll();

    T delete(T entity);

    T update(T entity);

    T findById(int id);

}
