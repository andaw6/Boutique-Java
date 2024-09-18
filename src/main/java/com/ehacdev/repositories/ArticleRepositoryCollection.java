package com.ehacdev.repositories;

import com.ehacdev.collections.CollectionRepository;
import com.ehacdev.entities.Article;
import com.ehacdev.repositories.interfaces.IArticleRepository;

import java.util.Collection;
import java.util.Optional;

public class ArticleRepositoryCollection extends CollectionRepository<Article> implements IArticleRepository {

    public ArticleRepositoryCollection(Collection<Article> entities) {
        super(entities);
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        return findAll().stream()
                .filter(article -> article.getTitle().equals(title))
                .findFirst();
    }

}
