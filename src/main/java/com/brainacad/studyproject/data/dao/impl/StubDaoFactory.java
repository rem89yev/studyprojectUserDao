package com.brainacad.studyproject.data.dao.impl;

import com.brainacad.studyproject.data.dao.DaoFactory;
import com.brainacad.studyproject.data.dao.UserDao;

/**
 * Created by User on 11/1/2016.
 */
public class StubDaoFactory extends DaoFactory {

    @Override
    public UserDao getUserDao() {
        return new StubUserDao();
    }

}
