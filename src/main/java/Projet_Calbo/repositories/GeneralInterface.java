package Projet_Calbo.repositories;

import java.util.List;
import java.util.Optional;

public interface GeneralInterface<T> {
    List<T> findAll();
    Optional<T> findById(T entity);
    T save(T entity);
    Optional<T> update(T entity);
    boolean delete(T entity);
    long count();
    List<T> findAll(int page, int pageSize);
}