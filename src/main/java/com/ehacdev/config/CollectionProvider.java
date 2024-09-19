package com.ehacdev.config;

import java.util.ArrayList;
import java.util.Collection;

import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.entities.Article;
import com.ehacdev.repositories.ArticleRepositoryJDBC;
import com.ehacdev.services.interfaces.IArticleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ehacdev.repositories.ArticleRepositoryCollection;
import com.ehacdev.repositories.interfaces.IArticleRepository;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class CollectionProvider {

    // Bean pour le profil "jdbc"
    @Bean
    @Profile("jdbc")
    public IArticleRepository articleRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new ArticleRepositoryJDBC(databaseFactory);
    }

    // Bean pour créer une instance de DatabaseFactory, requis par le profil "jdbc"
    @Bean
    @Profile("jdbc")
    public DatabaseFactory databaseFactory(DataSource dataSource) {
        return new DatabaseFactory(dataSource);
    }

    @Bean
    @Profile("in-memory")
    public IArticleRepository articleRepositoryCollection() {
        Collection<Article> articleCollection = new ArrayList<>();
        return new ArticleRepositoryCollection(articleCollection);
    }
}
