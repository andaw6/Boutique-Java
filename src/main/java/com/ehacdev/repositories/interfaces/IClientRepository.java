package com.ehacdev.repositories.interfaces;

import com.ehacdev.entities.Client;
import com.ehacdev.interfaces.IRepository;

import java.util.Optional;

public interface IClientRepository extends IRepository<Client> {
    Optional<Client> findByTelephone(String telephone);
}
