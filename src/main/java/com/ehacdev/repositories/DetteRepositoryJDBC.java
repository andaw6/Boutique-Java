package com.ehacdev.repositories;

import com.ehacdev.database.CrudRepository;
import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.entities.Dette;
import com.ehacdev.repositories.interfaces.IDetteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jdbc")
public class DetteRepositoryJDBC extends CrudRepository<Dette> implements IDetteRepository {
    public DetteRepositoryJDBC(DatabaseFactory databaseFactory) {
        super(databaseFactory);
        this.tableName = "dettes";
        this.type = Dette.class;
    }
}
