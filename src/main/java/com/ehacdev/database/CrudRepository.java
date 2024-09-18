package com.ehacdev.database;

import com.ehacdev.interfaces.IRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CrudRepository<T> implements IRepository<T> {
    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public T delete(T t) {
        return null;
    }

    @Override
    public Collection<T> findAll() {
        return List.of();
    }

    @Override
    public Optional<T> find(int id) {
        return Optional.empty();
    }
}
