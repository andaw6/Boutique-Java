package com.ehacdev.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface ICrud <T>{

    T save(T t);

    T update(T t);

    T delete(T t);

    Collection<T> findAll();

    Optional<T> find(int id);

}
