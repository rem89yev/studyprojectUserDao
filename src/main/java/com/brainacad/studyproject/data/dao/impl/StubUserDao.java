package com.brainacad.studyproject.data.dao.impl;

import com.brainacad.studyproject.data.core.StubDataHolder;
import com.brainacad.studyproject.data.dao.UserDao;
import com.brainacad.studyproject.data.domain.User;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by User on 11/1/2016.
 */
public class StubUserDao implements UserDao {

    @Override
    public User getUserByName(String username) {
        Collection<User> users = StubDataHolder.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    @Override
    public User get(int id) {
        Collection<User> users = StubDataHolder.getUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public int add(User entity) {
        return StubDataHolder.add(entity);
    }

    @Override
    public boolean delete(int id) {
        Collection<User> users = StubDataHolder.getUsers();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(User entity) {
        Collection<User> users = StubDataHolder.getUsers();
        for (User user : users) {
            if (user.getId() == entity.getId()) {
                user.setUsername(entity.getUsername());
                user.setPassword(entity.getPassword());
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<User> getAll() {
        return StubDataHolder.getUsers();
    }
}
