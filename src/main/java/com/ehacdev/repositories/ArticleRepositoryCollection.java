package com.ehacdev.repositories;

import com.ehacdev.collections.CollectionRepository;
import com.ehacdev.entities.Article;
import com.ehacdev.repositories.interfaces.IArticleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("in-memory")
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

    @Override
    public Boolean existsById(int id) {
        return find(id).isPresent();
    }

}
