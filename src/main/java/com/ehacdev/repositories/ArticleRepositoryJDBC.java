package com.ehacdev.repositories;

import com.ehacdev.database.CrudRepository;
import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.entities.Article;
import com.ehacdev.repositories.interfaces.IArticleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class ArticleRepositoryJDBC extends CrudRepository<Article> implements IArticleRepository {


    public ArticleRepositoryJDBC(DatabaseFactory databaseFactory) {
        super(databaseFactory);
        this.tableName = "articles";
        this.type = Article.class;
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        try (Connection connection = databaseFactory.getConnection()) {
            String query = "SELECT * FROM " + tableName + " WHERE title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Article article = mapRowToObject(resultSet);
                return Optional.of(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Boolean existsById(int id) {
        String query = "SELECT 1 FROM " + tableName + " WHERE id = ?";
        try (Connection connection = databaseFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Si le résultat existe, cela signifie que l'article existe
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Article save(Article article) {
        if (findByTitle(article.getTitle()).isPresent()) {
            System.out.println("Article with this title already exists.");
            return null;
        }
        try (Connection connection = databaseFactory.getConnection()) {
            String query = "INSERT INTO " + tableName + " (title, price, quantity, threshold) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setDouble(2, article.getPrice());
            preparedStatement.setInt(3, article.getQuantity());
            preparedStatement.setInt(4, article.getThreshold());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                article.setId(generatedKeys.getInt(1));
            }
            return article;
        } catch (SQLException e) {
            return traceErrorSql(e);
        }
    }

    @Override
    public Article update(Article article) {
        if (article == null || article.getId() <= 0) {
            throw new IllegalArgumentException("Article or Article ID cannot be null or zero");
        }
        String query = "UPDATE " + tableName + " SET title = ?, price = ?, quantity = ?, threshold = ? WHERE id = ?";
        try (Connection connection = databaseFactory.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, article.getTitle());
                preparedStatement.setDouble(2, article.getPrice());
                preparedStatement.setInt(3, article.getQuantity());
                preparedStatement.setInt(4, article.getThreshold());
                preparedStatement.setInt(5, article.getId());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    connection.commit();
                    return article;
                } else {
                    connection.rollback();
                    return null;
                }
            } catch (SQLException e) {
                return traceErrorSql(e);
            }
        } catch (SQLException e) {
            return traceErrorSql(e);
        }
    }


    @Override
    protected Article mapRowToObject(ResultSet resultSet) {
        try {
            Article article = new Article();
            article.setId(resultSet.getInt("id"));
            article.setTitle(resultSet.getString("title"));
            article.setPrice(resultSet.getDouble("price"));
            article.setQuantity(resultSet.getInt("quantity"));
            article.setThreshold(resultSet.getInt("threshold"));
            return article;
        } catch (SQLException e) {
            System.out.println("Erreur lors du mapping du champ");
            return traceErrorSql(e);
        }
    }

}
