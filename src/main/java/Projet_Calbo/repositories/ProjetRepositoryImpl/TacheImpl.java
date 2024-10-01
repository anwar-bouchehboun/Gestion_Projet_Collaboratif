package Projet_Calbo.repositories.ProjetRepositoryImpl;

import Projet_Calbo.model.Tache;
import Projet_Calbo.repositories.GeneralInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TacheImpl implements GeneralInterface<Tache> {
    private List<Tache> taches = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Tache> findAll() {
        return new ArrayList<>(taches);
    }

    @Override
    public Optional<Tache> findById(Tache entity) {
        return taches.stream()
                .filter(tache -> tache.getId().equals(entity.getId()))
                .findFirst();
    }

    @Override
    public Tache save(Tache entity) {
        if (entity.getId() == null) {
            entity.setId(nextId++);
        }
        taches.add(entity);
        return entity;
    }

    @Override
    public Optional<Tache> update(Tache entity) {
        for (int i = 0; i < taches.size(); i++) {
            if (taches.get(i).getId().equals(entity.getId())) {
                taches.set(i, entity);
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Tache entity) {
        return taches.removeIf(tache -> tache.getId().equals(entity.getId()));
    }

    @Override
    public long count() {
        return taches.size();
    }

    @Override
    public List<Tache> findAll(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, taches.size());
        return new ArrayList<>(taches.subList(start, end));
    }
}
