package com.brainacad.studyproject.data.dao.impl;

import com.brainacad.studyproject.data.core.ConnectionManager;
import com.brainacad.studyproject.data.dao.UserDao;
import com.brainacad.studyproject.data.domain.Role;
import com.brainacad.studyproject.data.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import static com.brainacad.studyproject.data.domain.Role.ADMIN;
import static com.brainacad.studyproject.data.domain.Role.USER;

/**
 * Created by User on 11/5/2016.
 */
public class JdbcUserDao implements UserDao {

    public static final String SELECT_FROM_USERS = "SELECT * FROM users";
    public static final String WHERE_USERNAME_LIKE = SELECT_FROM_USERS + " WHERE username LIKE ?";
    public static final String INSERT_INTO_USERS = "INSERT INTO users (username,password) VALUES (?, ?)";
    public static final String UPDATE_USERS = "UPDATE users SET username=?, password=? WHERE user_id=?";
    public static final String SELECT_FROM_USERS_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    public static final String DELETE_FROM_USERS = "DELETE FROM users WHERE user_id = ?";

    private ConnectionManager connectionManager
            = ConnectionManager.getInstance();

    @Override
    public User getUserByName(String username) {
        Connection connection = connectionManager.getConnection();
        User user = null;
        try {
            PreparedStatement statement =
                    connection.prepareStatement(WHERE_USERNAME_LIKE);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                user = new User();
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getInt("role_id") == 1 ? ADMIN : USER);
                }
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User get(int id) {
        Connection connection = connectionManager.getConnection();
        User user = null;
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SELECT_FROM_USERS_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                user = new User();
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getInt("role_id") == 1 ? ADMIN : USER);
                }
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int add(User user) {
        Connection connection = connectionManager.getConnection();
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_USERS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            id = statement.executeUpdate();
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            //TODO: log it
        }
        return id;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = connectionManager.getConnection();
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_FROM_USERS);
            statement.setInt(1, id);
            int i  = statement.executeUpdate();
            if (i == 0) {
                //TODO: log it
            } else {
                result = true;
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            //TODO: log it
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        Connection connection = connectionManager.getConnection();
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USERS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            int i = statement.executeUpdate();
            if (i == 0) {
                //TODO: exception and log
            } else {
                result = true;
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<User> getAll() {
        Connection connection = connectionManager.getConnection();
        Collection<User> users = new HashSet();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SELECT_FROM_USERS);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getInt("role_id") == 1 ? ADMIN : USER);
                    users.add(user);
                }
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
