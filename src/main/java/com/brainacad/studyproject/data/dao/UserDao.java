package com.brainacad.studyproject.data.dao;

import com.brainacad.studyproject.data.domain.User;

/**
 * Created by User on 11/1/2016.
 */
public interface UserDao extends CrudDao<User> {

    User getUserByName(String username);

}
