package com.ehacdev.repositories;

import com.ehacdev.database.CrudRepository;
import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.entities.User;
import com.ehacdev.repositories.interfaces.IUserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jdbc")
public class UserRepositoryJDBC extends CrudRepository<User> implements IUserRepository {
    public UserRepositoryJDBC(DatabaseFactory databaseFactory) {
        super(databaseFactory);
        this.tableName = "users";
        this.type = User.class;
    }
}
