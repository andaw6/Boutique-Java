package com.ehacdev.collections;

import java.util.Collection;
import java.util.Optional;

import com.ehacdev.interfaces.IRepository;
import com.ehacdev.interfaces.Identifiable;

public class CollectionRepository<T extends Identifiable> implements IRepository<T> {

    private final Collection<T> collection;

    public CollectionRepository(Collection<T> collection) {
        this.collection = collection;
    }

    @Override
    public T save(T entity) {
        Optional<T> existingEntity = find(entity.getId());
        if (existingEntity.isEmpty()) {
            collection.add(entity);
        } else {
            update(entity);
        }
        return entity;
    }

    @Override
    public T update(T entity) {
        Optional<T> existingEntity = find(entity.getId());
        if (existingEntity.isPresent()) {
            delete(existingEntity.get()); 
            collection.add(entity); 
        } else {
            throw new IllegalArgumentException("Entity with ID " + entity.getId() + " not found.");
        }
        return entity;
    }

    @Override
    public T delete(T entity) {
        boolean removed = collection.removeIf(e -> e.getId() == entity.getId());
        if (!removed) {
            throw new IllegalArgumentException("Entity with ID " + entity.getId() + " not found.");
        }
        return entity;
    }

    @Override
    public Collection<T> findAll() {
        return collection;
    }

    @Override
    public Optional<T> find(int id) {
        return collection.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst();
    }
}
