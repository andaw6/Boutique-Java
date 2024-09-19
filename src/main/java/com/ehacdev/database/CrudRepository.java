package com.ehacdev.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ehacdev.interfaces.IRepository;

@Repository
public class CrudRepository<T> implements IRepository<T> {

    protected final DatabaseFactory databaseFactory;
    protected Class<T> type;
    protected String tableName;

    @Autowired
    public CrudRepository(DatabaseFactory databaseFactory) {
        this.databaseFactory = databaseFactory;
    }

    @Override
    public T save(T t) {
        try (Connection connection = databaseFactory.getConnection()) {
            String query = "INSERT INTO " + tableName + " (fields...) VALUES (?, ?, ...)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T update(T t) {
        try (Connection connection = databaseFactory.getConnection()) {
            String query = "UPDATE " + tableName + " SET field1 = ?, field2 = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T delete(T t) {
        try (Connection connection = databaseFactory.getConnection()) {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Collection<T> findAll() {
        List<T> resultList = new ArrayList<>();
        try (Connection connection = databaseFactory.getConnection()) {
            String query = "SELECT * FROM " + tableName;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(mapRowToObject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Optional<T> find(int id) {
        try (Connection connection = databaseFactory.getConnection()) {
            String query = "SELECT * FROM " + tableName + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                T object = mapRowToObject(resultSet);
                return Optional.of(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    protected T mapRowToObject(ResultSet resultSet) {
        try {
            T obj = type.getDeclaredConstructor().newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Permet d'accéder aux champs privés

                try {
                    Object value = resultSet.getObject(field.getName());
                    field.set(obj, value);
                } catch (SQLException e) {
                    System.out.println("Erreur lors du mapping du champ : " + field.getName());
                    e.printStackTrace();
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
