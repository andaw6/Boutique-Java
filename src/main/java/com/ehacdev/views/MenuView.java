package com.ehacdev.views;

import com.ehacdev.utils.Console;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuView {

    private final ApplicationContext context;
    private final Scanner scanner;

    public MenuView(ApplicationContext context) {
        this.context = context;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choix;
        do {
            Console.clear();
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1-Gestion des clients");
            System.out.println("2-Gestion des utilisateurs");
            System.out.println("3-Gestion des paiements");
            System.out.println("4-Gestion des dettes");
            System.out.println("5-Gestion des articles");
            System.out.println("6-Quitter");
            System.out.print("Veuillez entrer votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    context.getBean(ClientView.class).show();
                    break;
                case 2:
                    context.getBean(UserView.class).show();
                    break;
                case 3:
                    context.getBean(PaiementView.class).show();
                    break;
                case 4:
                    context.getBean(DetteView.class).show();
                    break;
                case 5:
                    context.getBean(ArticleView.class).show();
                    break;
                case 6:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }

        } while (choix != 6);
    }
}