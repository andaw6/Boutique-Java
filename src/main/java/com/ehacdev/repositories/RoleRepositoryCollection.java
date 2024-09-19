package com.ehacdev.repositories;

import com.ehacdev.collections.CollectionRepository;
import com.ehacdev.entities.Role;
import com.ehacdev.repositories.interfaces.IRoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("in-memory")
public class RoleRepositoryCollection extends CollectionRepository<Role> implements IRoleRepository {
    public RoleRepositoryCollection(Collection<Role> collection) {
        super(collection);
    }

    @Override
    public int getIdCategorieByLibelle(String libelle) {
        return findAll().stream()
                .filter(categorie -> categorie.getLibelle().equals(libelle))
                .map(Role::getId)
                .findFirst()
                .orElse(-1);
    }

    @Override
    public String getLibelleById(int id) {
        return findAll().stream()
                .filter(categorie -> categorie.getId() == id)
                .map(Role::getLibelle)
                .findFirst()
                .orElse(null);
    }
}
