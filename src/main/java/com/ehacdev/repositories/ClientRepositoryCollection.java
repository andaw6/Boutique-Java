package com.ehacdev.repositories;

import com.ehacdev.collections.CollectionRepository;
import com.ehacdev.entities.Article;
import com.ehacdev.entities.Client;
import com.ehacdev.repositories.interfaces.IArticleRepository;
import com.ehacdev.repositories.interfaces.IClientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("in-memory")
public class ClientRepositoryCollection  extends CollectionRepository<Client> implements IClientRepository {

    public ClientRepositoryCollection(Collection<Client> collection) {
        super(collection);
    }

    @Override
    public Optional<Client> findByTelephone(String telephone) {
        return findAll().stream()
                .filter(client -> telephone.equals(client.getTelephone()))
                .findFirst();
    }
}
