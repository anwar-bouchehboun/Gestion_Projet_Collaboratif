package Projet_Calbo.services;

import Projet_Calbo.model.Tache;
import Projet_Calbo.repositories.ProjetRepositoryImpl.TacheImpl;

import java.util.List;
import java.util.Optional;

public class TacheService {
    private TacheImpl tacheRepository;

    public TacheService() {
        this.tacheRepository = new TacheImpl();
    }

    public List<Tache> findAll(int page, int pageSize) {
        return tacheRepository.findAll(page, pageSize);
    }

    public long countAll() {
        return tacheRepository.count();
    }

    public Optional<Tache> findById(Integer id) {
        Tache tache = new Tache(id, null, null, null, null, null, null);
        return tacheRepository.findById(tache);
    }

    public Tache save(Tache tache) {
        return tacheRepository.save(tache);
    }

    public Optional<Tache> update(Tache tache) {
        return tacheRepository.update(tache);
    }

    public boolean delete(Integer id) {
        Tache tache = new Tache(id, null, null, null, null, null, null);
        return tacheRepository.delete(tache);
    }
}