package Projet_Calbo.repositories;

import java.util.List;

public interface GeneralInterface<T> {
    T findById(Integer id);
    List<T> findAll(int page, int pageSize);
    boolean save(T entity);
    void update(T entity);
    void delete(Integer id);
    int countAll();
}
