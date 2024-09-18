package com.ehacdev.database;

import com.ehacdev.interfaces.IDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseFactory implements IDatabase {
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {

    }

    @Override
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        return null;
    }

    @Override
    public int executeUpdate(String query, Object... params) throws SQLException {
        return 0;
    }
}
