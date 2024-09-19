package com.ehacdev.repositories;

import com.ehacdev.collections.CollectionRepository;
import com.ehacdev.entities.Paiement;
import com.ehacdev.repositories.interfaces.IPaiementRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("in-memory")
public class PaiementRepositoryCollection extends CollectionRepository<Paiement> implements IPaiementRepository {
    public PaiementRepositoryCollection(Collection<Paiement> collection) {
        super(collection);
    }
}
