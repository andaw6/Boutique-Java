package com.ehacdev.repositories.interfaces;

import com.ehacdev.entities.Categorie;
import com.ehacdev.interfaces.IRepository;

public interface ICategorieRepository extends IRepository<Categorie> {

    int getIdCategorieByLibelle(String libelle);

    String getLibelleById(int id);

}
