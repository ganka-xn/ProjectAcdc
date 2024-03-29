package com.model.repository;

import com.model.entities.User;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {

    Collection<T> getAll();

    Optional<T> get(Long id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
