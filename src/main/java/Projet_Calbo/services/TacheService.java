package Projet_Calbo.services;

import java.util.List;

import Projet_Calbo.model.Tache;
import Projet_Calbo.repositories.ProjetRepositoryImpl.TacheImpl;

public class TacheService {
    private TacheImpl tacheRepository;

    public TacheService() {
        this.tacheRepository = new TacheImpl();
    }

    public Tache findById(Integer id) {
        return tacheRepository.findById(id);
    }

    public List<Tache> findAll(int page, int pageSize) {
        return tacheRepository.findAll(page, pageSize);
    }

    public void save(Tache tache) {
        tacheRepository.save(tache);
    }

    public void update(Tache tache) {
        tacheRepository.update(tache);
    }

    public void delete(Integer id) {
        tacheRepository.delete(id);
    }

    public int countAll() {
        return tacheRepository.countAll();
    }
}
