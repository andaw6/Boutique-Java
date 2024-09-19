package com.ehacdev.services;

import com.ehacdev.entities.Client;
import com.ehacdev.repositories.interfaces.IClientRepository;
import com.ehacdev.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    @Autowired
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.update(client);
    }

    @Override
    public Client delete(Client client) {
        return clientRepository.delete(client);
    }

    @Override
    public Collection<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> find(int id) {
        return clientRepository.find(id);
    }
}
