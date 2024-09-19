package com.ehacdev.repositories;

import com.ehacdev.database.CrudRepository;
import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.entities.Role;
import com.ehacdev.repositories.interfaces.IRoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Profile("jdbc")
public class RoleRepositoryJDBC extends CrudRepository<Role> implements IRoleRepository {
    public RoleRepositoryJDBC(DatabaseFactory databaseFactory) {
        super(databaseFactory);
        this.tableName = "roles";
        this.type = Role.class;
    }

    @Override
    public int getIdCategorieByLibelle(String libelle) {
        String query = "SELECT id FROM " + tableName + " WHERE libelle = ?";
        try (Connection connection = databaseFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, libelle);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            traceErrorSql(e);
        }
        return -1;
    }

    @Override
    public String getLibelleById(int id) {
        String query = "SELECT libelle FROM " + tableName + " WHERE id = ?";
        try (Connection connection = databaseFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("libelle");
            }
        } catch (SQLException e) {
            traceErrorSql(e);
        }
        return null;
    }

}
