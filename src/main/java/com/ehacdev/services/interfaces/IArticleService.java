package com.ehacdev.services.interfaces;

import com.ehacdev.entities.Article;
import com.ehacdev.interfaces.IService;

import java.util.Optional;

public interface IArticleService extends IService<Article> {

    Optional<Article> findByTitle(String title);


}
