package com.brainacad.studyproject.data.dao;

import java.util.Collection;

/**
 * Created by User on 11/10/2016.
 */
public interface CrudDao<E> {

    E get(int id);

    int add(E entity);

    boolean delete(int id);

    boolean update(E entity);

    Collection<E> getAll();

}
