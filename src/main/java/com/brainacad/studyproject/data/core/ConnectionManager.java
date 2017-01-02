package com.brainacad.studyproject.data.core;

import java.sql.*;

/**
 * Created by User on 11/5/2016.
 */
public class ConnectionManager {

    private static ConnectionManager instance;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return JdbcConnectionPool.getInstance().getConnection();
    }

    public void closeConnection(Connection connection) {
        JdbcConnectionPool.getInstance().releaseConnection(connection);
    }

}
