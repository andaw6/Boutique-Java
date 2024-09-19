package com.ehacdev.repositories.interfaces;

import com.ehacdev.entities.Role;
import com.ehacdev.interfaces.IRepository;

public interface IRoleRepository  extends IRepository<Role> {

    int getIdCategorieByLibelle(String libelle);

    String getLibelleById(int id);

}
