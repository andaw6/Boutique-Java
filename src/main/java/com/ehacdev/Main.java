package com.ehacdev;

import java.util.Collection;
import java.util.Optional;

import com.ehacdev.entities.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.ehacdev.config.AppConfig;
import com.ehacdev.entities.Article;
import com.ehacdev.services.interfaces.IArticleService;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@ComponentScan(basePackages = "com.ehacdev")
public class Main {
    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1);
        article.setTitle("Title");
        article.setPrice(100);
        article.setThreshold(3);
        article.setQuantity(10);

        // Chargement du contexte Spring
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Obtenir le service d'articles
        IArticleService articleService = context.getBean(IArticleService.class);

        // Sauvegarder l'article
        articleService.save(article);


        Optional<Article> myArticle = articleService.find(1);

        Collection<Article> myArticles = articleService.findAll();

        myArticles.forEach(System.out::println);

        // Afficher l'article sauvegardé
        System.out.println(myArticle);


    }
}