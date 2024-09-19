package com.ehacdev.repositories;

import com.ehacdev.collections.CollectionRepository;
import com.ehacdev.entities.User;
import com.ehacdev.repositories.interfaces.IUserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("in-memory")
public class UserRepositoryCollection extends CollectionRepository<User> implements IUserRepository {
    public UserRepositoryCollection(Collection<User> collection) {
        super(collection);
    }
}
