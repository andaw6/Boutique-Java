package com.ehacdev.config;

import java.util.ArrayList;

import com.ehacdev.services.interfaces.IArticleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ehacdev.repositories.ArticleRepositoryCollection;
import com.ehacdev.repositories.interfaces.IArticleRepository;

@Configuration
@ComponentScan(basePackages = "com.ehacdev")
public class CollectionProvider {

    @Bean
    public IArticleRepository articleRepository() {
        return new ArticleRepositoryCollection(new ArrayList<>());
    }


}