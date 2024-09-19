package com.ehacdev.services;

import com.ehacdev.entities.Dette;
import com.ehacdev.repositories.interfaces.IDetteRepository;
import com.ehacdev.services.interfaces.IDetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DetteService implements IDetteService {

    final private IDetteRepository detteRepository;

    @Autowired
    public DetteService(IDetteRepository detteRepository) {
        this.detteRepository = detteRepository;
    }

    @Override
    public Dette save(Dette dette) {
        return detteRepository.save(dette);
    }

    @Override
    public Dette update(Dette dette) {
        return detteRepository.update(dette);
    }

    @Override
    public Dette delete(Dette dette) {
        return detteRepository.delete(dette);
    }

    @Override
    public Collection<Dette> findAll() {
        return detteRepository.findAll();
    }

    @Override
    public Optional<Dette> find(int id) {
        return detteRepository.find(id);
    }
}
