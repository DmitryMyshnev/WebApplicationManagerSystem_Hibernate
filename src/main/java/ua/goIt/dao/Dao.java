package ua.goIt.dao;

import ua.goIt.model.Identity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identity> {

    void create(T entity);
    void update(T entity);
    void delete(T entity);
    Optional<T> getById(Long id);
    List<T> getAll();
}
