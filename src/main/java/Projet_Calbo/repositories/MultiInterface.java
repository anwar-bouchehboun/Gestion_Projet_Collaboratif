package Projet_Calbo.repositories;

import java.util.List;

public interface MultiInterface<T> {
    List<T> getPage(int page, int pageSize);
	long count();
	
    List<T> findByName(String name);
    
   

    
}
