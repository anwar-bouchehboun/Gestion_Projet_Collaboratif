package Projet_Calbo.services;

import java.util.List;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.repositories.ProjetRepositoryImpl.EquipeImpl;

public class EquipeService {
	
	public EquipeImpl equipe=new EquipeImpl();
	public EquipeService() {
	}
	
	public List<Equipe> getAll(){
		return equipe.getAll();
	}
	public void saveEquipe(Equipe e) {
		  
		equipe.save(e);
	}
	public void deleteEquipe(Equipe e) {
		equipe.delete(e);
	}
	public void updateEquipe(Equipe e) {
		equipe.update(e);
	}
	
	
	
	public Long countEquipes() {
		return equipe.count();
	}

	public List<Equipe> getEquipePage(int page, int pageSize) {
		
		return equipe.getPage(page,pageSize);
	}

}
