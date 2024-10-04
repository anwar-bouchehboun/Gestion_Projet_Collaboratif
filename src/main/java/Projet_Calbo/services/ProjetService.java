package Projet_Calbo.services;

import java.util.List;

import Projet_Calbo.model.Projet;
import Projet_Calbo.repositories.ProjetRepositoryImpl.ProjetImpl;

public class ProjetService {

    private ProjetImpl projetRepository;

    public ProjetService() {
        this.projetRepository = new ProjetImpl();
    }

    public boolean saveProjet(Projet projet) { 
        return projetRepository.save(projet);
    }

    public void updateProjet(Projet projet) {
        projetRepository.update(projet);
    }

    public void deleteProjet(Projet projet) {
        projetRepository.delete(projet);
    }

    public List<Projet> getAllProjets() {
        return projetRepository.getAll();
    }

    public Projet getById(int id) {
        return projetRepository.findById(id); 
    }
    
    public List<Projet> findByName(String name) {
        return projetRepository.findByName(name);
    }

}
