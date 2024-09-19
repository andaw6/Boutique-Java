package com.ehacdev.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.entities.*;
import com.ehacdev.interfaces.IDatabase;
import com.ehacdev.repositories.*;
import com.ehacdev.repositories.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ehacdev.repositories.ArticleRepositoryCollection;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class CollectionProvider {


    @Bean
    @Profile("jdbc")
    public DatabaseFactory databaseFactory(DataSource dataSource) {
        return new DatabaseFactory(dataSource);
    }

    /***  ***/

    @Bean
    @Profile("jdbc")
    public IArticleRepository articleRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new ArticleRepositoryJDBC(databaseFactory);
    }

    @Bean
    @Profile("in-memory")
    public IArticleRepository articleRepositoryCollection() {
        Collection<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "Article 1", 100.0, 50, 10, null, LocalDateTime.now(), LocalDateTime.now()));
        articles.add(new Article(2, "Article 2", 200.0, 30, 5, null, LocalDateTime.now(), LocalDateTime.now()));
        articles.add(new Article(3, "Article 3", 300.0, 20, 3, null, LocalDateTime.now(), LocalDateTime.now()));
        return new ArticleRepositoryCollection(articles);
    }

    /***  ***/

    @Bean
    @Profile("in-memory")
    public IClientRepository clientRepositoryCollection() {
        Collection<Client> clientCollection = new ArrayList<>();
        return new ClientRepositoryCollection(clientCollection);
    }

    @Bean
    @Profile("jdbc")
    public IClientRepository clientRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new ClientRepositoryJDBC(databaseFactory);
    }

    /***  ***/


    @Bean
    @Profile("in-memory")
    public ICategorieRepository categorieRepositoryCollection() {
        Collection<Categorie> categories = new ArrayList<>();
        categories.add(new Categorie(1, "silver", null, LocalDateTime.now(), LocalDateTime.now()));
        categories.add(new Categorie(2, "gold", null, LocalDateTime.now(), LocalDateTime.now()));
        categories.add(new Categorie(3, "bronze", null, LocalDateTime.now(), LocalDateTime.now()));
        return new CategorieRepositoryCollection(categories);
    }

    @Bean
    @Profile("jdbc")
    public ICategorieRepository categorieRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new CategorieRepositoryJDBC(databaseFactory);
    }

    /***  ***/


    @Bean
    @Profile("in-memory")
    public IDetteRepository detteRepositoryCollection() {
        Collection<Dette> detteCollection = new ArrayList<>();
        return new DetteRepositoryCollection(detteCollection);
    }

    @Bean
    @Profile("jdbc")
    public IDetteRepository detteRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new DetteRepositoryJDBC(databaseFactory);
    }

    /***  ***/

    @Bean
    @Profile("in-memory")
    public IPaiementRepository paiementRepositoryCollection() {
        Collection<Paiement> paiementCollection = new ArrayList<>();
        return new PaiementRepositoryCollection(paiementCollection);
    }

    @Bean
    @Profile("jdbc")
    public IPaiementRepository paiementRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new PaiementRepositoryJDBC(databaseFactory);
    }

    /***  ***/

    @Bean
    @Profile("in-memory")
    public IRoleRepository roleRepositoryCollection() {
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(1, "admin", null, LocalDateTime.now(), LocalDateTime.now()));
        roles.add(new Role(2, "client", null, LocalDateTime.now(), LocalDateTime.now()));
        roles.add(new Role(3, "boutiquier", null, LocalDateTime.now(), LocalDateTime.now()));
        return new RoleRepositoryCollection(roles);
    }


    @Bean
    @Profile("jdbc")
    public IRoleRepository roleRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new RoleRepositoryJDBC(databaseFactory);
    }

    /***  ***/

    @Bean
    @Profile("in-memory")
    public IUserRepository userRepositoryCollection() {
        Collection<User> userCollection = new ArrayList<>();
        return new UserRepositoryCollection(userCollection);
    }


    @Bean
    @Profile("jdbc")
    public IUserRepository userRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new UserRepositoryJDBC(databaseFactory);
    }

}
