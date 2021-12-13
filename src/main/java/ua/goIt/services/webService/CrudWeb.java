package ua.goIt.services.webService;


import java.util.List;
import java.util.Optional;

public interface CrudWeb<T> {
    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> getAll();

    Optional<T> findById(Long id);

}
