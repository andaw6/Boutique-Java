package com.ehacdev.services.interfaces;

import com.ehacdev.entities.Categorie;
import com.ehacdev.interfaces.IService;

public interface ICategorieService extends IService<Categorie> {

    int getIdCategorieByLibelle(String libelle);

    String getLibelleById(int id);

}
