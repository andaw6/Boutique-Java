package com.ehacdev.services;

import com.ehacdev.entities.Categorie;
import com.ehacdev.repositories.interfaces.ICategorieRepository;
import com.ehacdev.services.interfaces.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategorieService implements ICategorieService {

    private final ICategorieRepository categorieRepository;

    @Autowired
    public CategorieService(ICategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Categorie save(Categorie categorie) {
         return categorieRepository.save(categorie);
    }

    @Override
    public Categorie update(Categorie categorie) {
        return categorieRepository.update(categorie);
    }

    @Override
    public Categorie delete(Categorie categorie) {
        return categorieRepository.delete(categorie);
    }

    @Override
    public Collection<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    @Override
    public Optional<Categorie> find(int id) {
        return categorieRepository.find(id);
    }

    @Override
    public int getIdCategorieByLibelle(String libelle) {
        return categorieRepository.getIdCategorieByLibelle(libelle);
    }

    @Override
    public String getLibelleById(int id) {
        return categorieRepository.getLibelleById(id);
    }
}
