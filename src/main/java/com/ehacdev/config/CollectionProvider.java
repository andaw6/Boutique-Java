package com.ehacdev.config;

import com.ehacdev.entities.Article;
import com.ehacdev.repositories.ArticleRepositoryCollection;
import com.ehacdev.repositories.interfaces.IArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ComponentScan(basePackages = "com.ehacdev")
public class CollectionProvider {

    @Bean
    public IArticleRepository articleRepository() {
        return new ArticleRepositoryCollection(new ArrayList<Article>());
    }
}
