package Projet_Calbo.repositories.ProjetRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Projet_Calbo.model.Tache;
import Projet_Calbo.repositories.GeneralInterface;

public class TacheImpl implements GeneralInterface<Tache> {
    private Map<Integer, Tache> taches = new HashMap<>();
    private Integer nextId = 1;

    @Override
    public Tache findById(Integer id) {
        return taches.get(id);
    }

    @Override
    public List<Tache> findAll(int page, int pageSize) {
        List<Tache> allTaches = new ArrayList<>(taches.values());
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, allTaches.size());
        return allTaches.subList(start, end);
    }

    @Override
    public boolean save(Tache entity) {
        if (entity.getId() == null) {
            entity.setId(nextId++);
        }
        taches.put(entity.getId(), entity);
        return true;
    }

    @Override
    public void update(Tache entity) {
        if (taches.containsKey(entity.getId())) {
            taches.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Integer id) {
        taches.remove(id);
    }

    @Override
    public int countAll() {
        return taches.size();
    }
}
