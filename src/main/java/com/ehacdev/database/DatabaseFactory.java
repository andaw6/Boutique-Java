package com.ehacdev.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehacdev.interfaces.IDatabase;

@Component
public class DatabaseFactory implements IDatabase {

    private final DataSource dataSource;

    @Autowired
    public DatabaseFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();  
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) { 
            connection.close();
        }
    }

    @Override
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        setParameters(statement, params);
        return statement.executeQuery();
    }

    @Override
    public int executeUpdate(String query, Object... params) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        setParameters(statement, params);
        int rowsAffected = statement.executeUpdate();
        closeConnection(connection);
        return rowsAffected;
    }

    private void setParameters(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }
}
