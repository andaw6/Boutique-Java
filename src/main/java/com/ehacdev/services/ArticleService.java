package com.ehacdev.services;

import com.ehacdev.entities.Article;
import com.ehacdev.repositories.interfaces.IArticleRepository;
import com.ehacdev.services.interfaces.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {

    private final IArticleRepository articleRepository;

    @Autowired
    public ArticleService(IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Recherche un article par son titre.
     *
     * @param title le titre de l'article
     * @return l'article correspondant au titre s'il est trouvé, sinon un Optional vide
     */
    @Override
    public Optional<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    /**
     * Sauvegarde un nouvel article.
     *
     * @param article l'article à sauvegarder
     * @return l'article sauvegardé
     */
    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    /**
     * Met à jour un article existant.
     *
     * @param article l'article à mettre à jour
     * @return l'article mis à jour
     */
    @Override
    public Article update(Article article) {
        // Assurez-vous que l'article existe avant de tenter la mise à jour
        if (articleRepository.existsById(article.getId())) {
            return articleRepository.save(article);
        }
        throw new IllegalArgumentException("Article with id " + article.getId() + " does not exist.");
    }

    /**
     * Supprime un article.
     *
     * @param article l'article à supprimer
     * @return l'article supprimé
     */
    @Override
    public Article delete(Article article) {
        if (articleRepository.existsById(article.getId())) {
            articleRepository.delete(article);
            return article;
        }
        throw new IllegalArgumentException("Article with id " + article.getId() + " does not exist.");
    }

    /**
     * Récupère tous les articles.
     *
     * @return une collection de tous les articles
     */
    @Override
    public Collection<Article> findAll() {
        return articleRepository.findAll();
    }

    /**
     * Recherche un article par son ID.
     *
     * @param id l'identifiant de l'article
     * @return l'article correspondant à l'ID s'il est trouvé, sinon un Optional vide
     */
    @Override
    public Optional<Article> find(int id) {
        return articleRepository.find(id);
    }
}
