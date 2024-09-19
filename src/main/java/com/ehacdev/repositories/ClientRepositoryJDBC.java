package com.ehacdev.repositories;

import com.ehacdev.database.CrudRepository;
import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.entities.Client;
import com.ehacdev.repositories.interfaces.IClientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class ClientRepositoryJDBC extends CrudRepository<Client> implements IClientRepository {

    public ClientRepositoryJDBC(DatabaseFactory databaseFactory) {
        super(databaseFactory);
        this.tableName = "clients";
        this.type = Client.class;
    }

    @Override
    public Optional<Client> findByTelephone(String telephone) {
        String query = "SELECT * FROM " + tableName + " WHERE telephone = ?";
        return executeQuery(query, preparedStatement -> preparedStatement.setString(1, telephone));
    }

    @Override
    public Client save(Client client) {
        String query = "INSERT INTO " + tableName + " (surname, address, qrcode, maxMontant, user_id, categorie_id, telephone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(query, preparedStatement -> {
            setClientParameters(preparedStatement, client);
        });
    }

    @Override
    public Client update(Client client) {
        String query = "UPDATE " + tableName + " SET surname = ?, address = ?, qrcode = ?, max_montant = ?, user_id = ?, categorie_id = ?, telephone = ? WHERE id = ?";
        return executeUpdate(query, preparedStatement -> {
            setClientParameters(preparedStatement, client);
            preparedStatement.setInt(8, client.getId());
        });
    }

    // Méthode générique pour exécuter une requête de sélection
    private Optional<Client> executeQuery(String query, StatementSetter setter) {
        try (Connection connection = databaseFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setter.setValues(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Client client = mapRowToObject(resultSet);
                return Optional.of(client);
            }
        } catch (SQLException e) {
            traceErrorSql(e);
        }
        return Optional.empty();
    }

    // Méthode générique pour exécuter une requête d'insertion ou de mise à jour
    private Client executeUpdate(String query, StatementSetter setter) {
        try (Connection connection = databaseFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setter.setValues(preparedStatement);
            preparedStatement.executeUpdate();
            return null; // Adjust return value as needed
        } catch (SQLException e) {
            traceErrorSql(e);
            return null;
        }
    }

    // Méthode pour définir les paramètres du PreparedStatement pour un Client
    private void setClientParameters(PreparedStatement preparedStatement, Client client) throws SQLException {
        preparedStatement.setString(1, client.getSurname());
        preparedStatement.setString(2, client.getAddress());
        preparedStatement.setString(3, client.getQrcode());
        preparedStatement.setInt(4, client.getMax_montant());
        Optional<Integer> userIdOpt = client.getUser_id();
        if (userIdOpt.isPresent()) {
            preparedStatement.setInt(5, userIdOpt.get());
        } else {
            preparedStatement.setNull(5, java.sql.Types.INTEGER);
        }
        preparedStatement.setInt(6, client.getCategorie_id());
        preparedStatement.setString(7, client.getTelephone());
    }


    // Interface fonctionnelle pour définir les valeurs des PreparedStatement
    @FunctionalInterface
    private interface StatementSetter {
        void setValues(PreparedStatement preparedStatement) throws SQLException;
    }
}
