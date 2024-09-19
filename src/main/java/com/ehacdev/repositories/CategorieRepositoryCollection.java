package com.ehacdev.repositories;

import com.ehacdev.collections.CollectionRepository;
import com.ehacdev.entities.Categorie;
import com.ehacdev.repositories.interfaces.ICategorieRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("in-memory")
public class CategorieRepositoryCollection extends CollectionRepository<Categorie> implements ICategorieRepository {
    public CategorieRepositoryCollection(Collection<Categorie> collection) {
        super(collection);
    }

    @Override
    public int getIdCategorieByLibelle(String libelle) {
        return findAll().stream()
                .filter(categorie -> categorie.getLibelle().equals(libelle))
                .map(Categorie::getId)
                .findFirst()
                .orElse(-1);
    }

    @Override
    public String getLibelleById(int id) {
        return findAll().stream()
                .filter(categorie -> categorie.getId() == id)
                .map(Categorie::getLibelle)
                .findFirst()
                .orElse(null);
    }
}
