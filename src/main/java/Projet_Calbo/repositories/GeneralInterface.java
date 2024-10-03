package Projet_Calbo.repositories;

import java.util.List;

public interface GeneralInterface<T> {
	boolean save(T entity);
	void update(T entity);
	void delete(T entity);
	List<T> getAll();
	T findById(Integer id);
	List<T> getPage(int page, int pageSize);
	long count();
}
