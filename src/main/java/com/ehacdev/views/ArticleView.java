package com.ehacdev.views;

import com.ehacdev.entities.Article;
import com.ehacdev.services.interfaces.IArticleService;
import com.ehacdev.utils.Console;
import com.ehacdev.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ArticleView extends View {

    private final IArticleService articleService;

    @Autowired
    public ArticleView(IArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public void show() {
        int choix;
        do {
            Console.clear();
            showMenu();
            System.out.print("Veuillez entrer votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    creerArticle();
                    break;
                case 2:
                    listerArticles();
                    break;
                case 3:
                    System.out.println("Retour au menu principal");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }

        } while (choix != 3);
    }

private void showMenu() {
        System.out.println("\n=== Menu Article ===");
        System.out.println("1-Créer un nouvel article");
        System.out.println("2-Lister les articles");
        System.out.println("3-Retour au menu principal");
    }

    private void creerArticle() {
        Console.clear();
        System.out.println("=== Création d'un nouvel article ===");

        String libelle;
        do {
            System.out.print("Entrez le libellé de l'article (min 3 caractères) : ");
            libelle = scanner.nextLine();
            if (!ValidatorUtil.validateString(libelle, 3)) {
                System.out.println("Le libellé doit contenir au moins 3 caractères.");
            }
        } while (!ValidatorUtil.validateString(libelle, 3));

        double prix;
        do {
            System.out.print("Entrez le prix de l'article : ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next();
            }
            prix = scanner.nextDouble();
            scanner.nextLine(); // Consommer la nouvelle ligne
            if (prix <= 0) {
                System.out.println("Le prix doit être supérieur à 0.");
            }
        } while (prix <= 0);

        int quantite;
        do {
            System.out.print("Entrez la quantité en stock : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre entier valide.");
                scanner.next();
            }
            quantite = scanner.nextInt();
            scanner.nextLine();
            if (quantite < 0) {
                System.out.println("La quantité ne peut pas être négative.");
            }
        } while (quantite < 0);

        Article nouvelArticle = new Article();
        nouvelArticle.setTitle(libelle);
        nouvelArticle.setPrice(prix);
        nouvelArticle.setQuantity(quantite);
        nouvelArticle.setThreshold(1);
        nouvelArticle.setCreatedAt(LocalDateTime.now());
        nouvelArticle.setUpdatedAt(LocalDateTime.now());

        articleService.save(nouvelArticle);
        System.out.println("Article créé avec succès !");
    }

    private void listerArticles() {
        Console.clear();
        var articles = articleService.findAll();
        if (articles.isEmpty()) {
            System.out.println("Aucun article trouvé.");
        } else {
            System.out.println("Liste des articles : ");
            articles.forEach(article -> {
                System.out.println("- " + article.getTitle() + " (Prix: " + article.getPrice() + " FCFA, Quantité: " + article.getQuantity() + ")");
            });
        }
        System.out.println("Appuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
}
