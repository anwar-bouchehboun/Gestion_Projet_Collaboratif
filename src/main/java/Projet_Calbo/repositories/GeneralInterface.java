package Projet_Calbo.repositories;

public interface GeneralInterface<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
