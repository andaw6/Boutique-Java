package com.ehacdev.repositories.interfaces;

import com.ehacdev.entities.Article;
import com.ehacdev.interfaces.IRepository;

import java.util.Optional;

public interface IArticleRepository extends IRepository<Article> {

    Optional<Article> findByTitle(String title);

    Boolean existsById(int id);

}
