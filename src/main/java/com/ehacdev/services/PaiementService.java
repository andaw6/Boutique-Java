package com.ehacdev.services;

import com.ehacdev.entities.Paiement;
import com.ehacdev.repositories.interfaces.IPaiementRepository;
import com.ehacdev.services.interfaces.IPaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Autowired
    public PaiementService(IPaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }


    @Override
    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement update(Paiement paiement) {
        return paiementRepository.update(paiement);
    }

    @Override
    public Paiement delete(Paiement paiement) {
        return paiementRepository.delete(paiement);
    }

    @Override
    public Collection<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    @Override
    public Optional<Paiement> find(int id) {
        return paiementRepository.find(id);
    }
}
