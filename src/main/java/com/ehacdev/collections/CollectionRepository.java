package com.ehacdev.collections;

import com.ehacdev.interfaces.IRepository;
import com.ehacdev.interfaces.Identifiable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CollectionRepository<T extends Identifiable> implements IRepository<T> {

    private final Collection<T> collections;

    public CollectionRepository(Collection<T> collections) {
        this.collections = collections;
    }


    @Override
    public T save(T t) {
        collections.add(t);
        return t;
    }

    @Override
    public T update(T t) {
        T init = delete(t);
        save(init);
        return init;
    }

    @Override
    public T delete(T t) {
        collections.removeIf(entity -> entity.getId() == t.getId());
        return t;
    }

    @Override
    public Collection<T> findAll() {
        return collections;
    }

    @Override
    public Optional<T> find(int id) {
        return collections.stream().filter(entity -> entity.getId() == id).findFirst();
    }
}
