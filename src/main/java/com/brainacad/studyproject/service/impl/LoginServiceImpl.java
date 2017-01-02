package com.brainacad.studyproject.service.impl;

import com.brainacad.studyproject.data.dao.DaoFactory;
import com.brainacad.studyproject.data.dao.UserDao;
import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.service.LoginService;

/**
 * Created by User on 11/1/2016.
 */
public class LoginServiceImpl implements LoginService {

    private UserDao userDao = DaoFactory.getDaoFactory().getUserDao();

    @Override
    public boolean login(String username, String password) {
        User user = userDao.getUserByName(username);
        return user != null && password.equals(user.getPassword());
    }

}
