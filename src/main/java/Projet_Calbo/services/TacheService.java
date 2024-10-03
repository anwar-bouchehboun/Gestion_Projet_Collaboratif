package Projet_Calbo.services;

import java.util.List;
import java.util.Map;

import Projet_Calbo.model.Members;
import Projet_Calbo.model.Projet;
import Projet_Calbo.model.Tache;
import Projet_Calbo.repositories.ProjetRepositoryImpl.TacheImpl;

public class TacheService {
    private TacheImpl tacheRepository;

    public TacheService() {
        this.tacheRepository = new TacheImpl();
    }

    public boolean addTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    public void updateTache(Tache tache) {
        tacheRepository.update(tache);
    }

    public void deleteTache(Tache tache) {
        tacheRepository.delete(tache);
    }

    public List<Tache> getAllTaches() {
        return tacheRepository.getAll();
    }

    public Tache getTacheById(int id) {
        return tacheRepository.findById(id);
    }

    public List<Tache> getTachesByProjet(int projetId) {
        return tacheRepository.getTachesByProjet(projetId);
    }
    public List<Tache> getTachePage(int page, int pageSize) {
        return tacheRepository.getPage(page, pageSize);
    }
    
    public int getTotalPages(int pageSize) {
        long totalCount = tacheRepository.count();
        return (int) Math.ceil((double) totalCount / pageSize);
    }
    public Map<Members, List<Tache>> getMembersAndTasksForProject(Projet projet) {
        return tacheRepository.getMembersAndTasksForProject(projet);
    }
}
