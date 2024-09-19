package com.ehacdev.repositories;

import com.ehacdev.collections.CollectionRepository;
import com.ehacdev.entities.Dette;
import com.ehacdev.repositories.interfaces.IDetteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("in-memory")
public class DetteRepositoryCollection extends CollectionRepository<Dette> implements IDetteRepository {
    public DetteRepositoryCollection(Collection<Dette> collection) {
        super(collection);
    }
}
