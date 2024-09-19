package com.ehacdev.database;

import com.ehacdev.entities.*;
import com.ehacdev.repositories.*;
import com.ehacdev.repositories.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Component
@Profile("in-memory")
public class DatabaseSeeder {

    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final IClientRepository clientRepository;
    private final ICategorieRepository categorieRepository;
    private final IArticleRepository articleRepository;

    @Autowired
    public DatabaseSeeder(IRoleRepository roleRepository, IUserRepository userRepository, IClientRepository clientRepository,
                          ICategorieRepository categorieRepository, IArticleRepository articleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.categorieRepository = categorieRepository;
        this.articleRepository = articleRepository;
        System.out.println("this is a test from seeder");
    }

    public void run(String... args) throws Exception {
        seedRoles();
        seedCategories();
        seedArticles();
        seedUsers();
        seedClients();
    }

    public void seedRoles() {
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(1, "Admin", null, LocalDateTime.now(), LocalDateTime.now()));
        roles.add(new Role(2, "Client", null, LocalDateTime.now(), LocalDateTime.now()));
        roles.add(new Role(3, "Boutiquier", null, LocalDateTime.now(), LocalDateTime.now()));
        for (Role role : roles) {
            roleRepository.save(role);
        }
    }

    public void seedArticles() {
        Collection<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "Article 1", 100.0, 50, 10, null, LocalDateTime.now(), LocalDateTime.now()));
        articles.add(new Article(2, "Article 2", 200.0, 30, 5, null, LocalDateTime.now(), LocalDateTime.now()));
        articles.add(new Article(3, "Article 3", 300.0, 20, 3, null, LocalDateTime.now(), LocalDateTime.now()));
        for (Article article : articles) {
            articleRepository.save(article);
        }
    }

    public void seedClients() {
        Role adminRole = roleRepository.find(1).orElseThrow(() -> new RuntimeException("Role not found"));
        Role clientRole = roleRepository.find(2).orElseThrow(() -> new RuntimeException("Role not found"));
        Collection<Client> clients = new ArrayList<>();
        clients.add(new Client(1, "John Doe", "123 Main St", "555-1234", "qrcode-1", 1000, userRepository.find(1).orElse(null), categorieRepository.find(1).orElse(null), null, LocalDateTime.now(), LocalDateTime.now()));
        clients.add(new Client(2, "Jane Doe", "456 Elm St", "555-5678", "qrcode-2", 1500, userRepository.find(2).orElse(null), categorieRepository.find(2).orElse(null), null, LocalDateTime.now(), LocalDateTime.now()));
        for (Client client : clients) {
            clientRepository.save(client);
        }
    }

    public void seedCategories() {
        Collection<Categorie> categories = new ArrayList<>();
        categories.add(new Categorie(1, "silver", null, LocalDateTime.now(), LocalDateTime.now()));
        categories.add(new Categorie(2, "gold", null, LocalDateTime.now(), LocalDateTime.now()));
        categories.add(new Categorie(3, "bronze", null, LocalDateTime.now(), LocalDateTime.now()));
        for(Categorie categorie: categories) {
            categorieRepository.save(categorie);
        }
    }

    public void seedUsers() {
        Role adminRole = roleRepository.find(1).orElseThrow(() -> new RuntimeException("Role not found"));
        Role clientRole = roleRepository.find(2).orElseThrow(() -> new RuntimeException("Role not found"));
        Collection<User> users = new ArrayList<>();
        users.add(new User(1, "Elhadji", "Ciss", "cissandaw@gmail.com", "password", null, adminRole, null,  LocalDateTime.now(), LocalDateTime.now()));
        users.add(new User(2, "Mamadou", "Gueye", "mamadou@gmail.com", "password", null, clientRole, null,  LocalDateTime.now(), LocalDateTime.now()));
        for (User user : users) {
            userRepository.save(user);
        }
    }

}
