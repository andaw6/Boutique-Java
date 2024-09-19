package com.ehacdev.config;

import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.repositories.ArticleRepositoryJDBC;
import com.ehacdev.repositories.interfaces.IArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ehacdev")
public class RepositoryConfig {

    @Bean
    public IArticleRepository articleRepositoryJDBC(DatabaseFactory databaseFactory) {
        return new ArticleRepositoryJDBC(databaseFactory);
    }
}
