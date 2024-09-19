package com.ehacdev.views;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehacdev.entities.Categorie;
import com.ehacdev.entities.Client;
import com.ehacdev.services.interfaces.ICategorieService;
import com.ehacdev.services.interfaces.IClientService;
import com.ehacdev.utils.Console;
import com.ehacdev.utils.ValidatorUtil;

@Component
public class ClientView extends View {

    private final IClientService clientService;
    private final ICategorieService categorieService;

    @Autowired
    public ClientView(IClientService clientService, ICategorieService categorieService) {
        this.clientService = clientService;
        this.categorieService = categorieService;
    }

    public void creerClient() {
        Console.clear();
        System.out.println("=== Création d'un nouveau client ===");
        String surname;
        do {
            System.out.print("Entrez le nom du client (min 3 caractères) : ");
            surname = scanner.nextLine();
            if (!ValidatorUtil.validateString(surname, 3)) {
                System.out.println("Le nom doit contenir au moins 3 caractères.");
            }
        } while (!ValidatorUtil.validateString(surname, 3));

        String adresse;
        do {
            System.out.print("Entrez l'adresse du client : ");
            adresse = scanner.nextLine();
            if (!ValidatorUtil.validateRequiredField(adresse)) {
                System.out.println("L'adresse ne peut pas être vide.");
            }
        } while (!ValidatorUtil.validateRequiredField(adresse));

        String telephone;
        do {
            System.out.print("Entrez le numéro de téléphone du client (10 chiffres) : ");
            telephone = scanner.nextLine();
            if (!ValidatorUtil.validatePhoneNumber(telephone)) {
                System.out.println("Le numéro de téléphone doit contenir 10 chiffres.");
            }
        } while (!ValidatorUtil.validatePhoneNumber(telephone));

        Categorie categorie = selectionnerCategorie();

        Client nouveauClient = new Client();
        nouveauClient.setSurname(surname);
        nouveauClient.setAdresse(adresse);
        nouveauClient.setTelephone(telephone);
        nouveauClient.setQrcode("qrcode");
        nouveauClient.setCategorie(categorie);
        nouveauClient.setCreatedAt(LocalDateTime.now());
        nouveauClient.setUpdatedAt(LocalDateTime.now());
        nouveauClient.setMaxMontant(0);
        clientService.save(nouveauClient);
        System.out.println("Client créé avec succès !");
    }

    private static void showMenu() {
        System.out.println("\n=== Menu Client ===");
        System.out.println("1-Créer un nouveau client");
        System.out.println("2-Lister les clients");
        System.out.println("3-Quitter");
    }

    @Override
    public void show() {
        int choix;
        do {
            Console.clear();
            showMenu();
            System.out.print("Veuillez entrer votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    creerClient();
                    break;
                case 2:
                    listerClients();
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }

        } while (choix != 3);
    }

    private void listerClients() {
        var clients = clientService.findAll();
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            System.out.println("Liste des clients : ");
            clients.forEach(client -> {
                System.out.println("- " + client.getSurname() + " (" + client.getTelephone() + ")");
            });
        }
    }

    // Méthode pour afficher et sélectionner une catégorie
    private Categorie selectionnerCategorie() {
        Collection<Categorie> categories = categorieService.findAll();
        if (categories.isEmpty()) {
            System.out.println("Aucune catégorie disponible.");
            return null;
        }

        System.out.println(categories.toString());

        System.out.println("=== Sélectionnez une catégorie ===");

        return new Categorie(1, "bronze", null, LocalDateTime.now(), LocalDateTime.now());

        /*
         * List<Categorie> categoryList = new ArrayList<>(categories); // Convertir la
         * collection en liste pour l'accès par index
         * int i = 1;
         * for (Categorie categorie : categoryList) {
         * System.out.println((i++) + " - " + categorie.getLibelle());
         * }
         * 
         * int choixCategorie = -1;
         * do {
         * System.out.print("Entrez le numéro de la catégorie : ");
         * while (!scanner.hasNextInt()) { // Vérifier si l'entrée est un entier
         * System.out.println("Entrée invalide, veuillez entrer un numéro.");
         * scanner.next(); // Consommer l'entrée non valide
         * }
         * choixCategorie = scanner.nextInt();
         * scanner.nextLine(); // Consommer la nouvelle ligne
         * 
         * if (choixCategorie < 1 || choixCategorie > categoryList.size()) {
         * System.out.println("Choix invalide, veuillez réessayer.");
         * }
         * } while (choixCategorie < 1 || choixCategorie > categoryList.size());
         * 
         * return categoryList.get(choixCategorie - 1); // Retourner la catégorie
         * sélectionnée
         */
    }

}
