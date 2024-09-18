package com.ehacdev.interfaces;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDatabase {

    Connection getConnection() throws SQLException;

    void closeConnection(Connection connection) throws SQLException;

    ResultSet executeQuery(String query, Object... params) throws SQLException;

    int executeUpdate(String query, Object... params) throws SQLException;

}
