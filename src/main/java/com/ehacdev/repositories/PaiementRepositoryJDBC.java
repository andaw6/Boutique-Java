package com.ehacdev.repositories;

import com.ehacdev.database.CrudRepository;
import com.ehacdev.database.DatabaseFactory;
import com.ehacdev.entities.Paiement;
import com.ehacdev.repositories.interfaces.IPaiementRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jdbc")
public class PaiementRepositoryJDBC extends CrudRepository<Paiement> implements IPaiementRepository {
    public PaiementRepositoryJDBC(DatabaseFactory databaseFactory) {
        super(databaseFactory);
        this.tableName = "paiements";
        this.type = Paiement.class;
    }
}
