package Projet_Calbo.repositories.ProjetRepositoryImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Projet_Calbo.config.DatabaseConnection;
import Projet_Calbo.model.Projet;
import Projet_Calbo.repositories.GeneralInterface;

public class ProjetImp implements GeneralInterface<Projet> {

    @Override
    public boolean save(Projet entity) {
        String sql = "INSERT INTO projet (nom, description, dateDebut, dateFin, statut, equipet_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getNom());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, Date.valueOf(entity.getDateDebut()));
            statement.setDate(4, Date.valueOf(entity.getDateFin()));
            statement.setString(5, entity.getStatut().toString()); 
            statement.setInt(6, entity.getEquipe().getId());

            int rowInserted = statement.executeUpdate();
            return rowInserted > 0;
        } catch (SQLException e) {
            System.out.println("Erreur dans la création d'un projet: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void update(Projet entity) {
        // TODO: Implement update functionality
    }

    @Override
    public void delete(Projet entity) {
        // TODO: Implement delete functionality
    }

    @Override
    public List<Projet> getAll() {
        // TODO: Implement getAll functionality
        return null;
    }

    @Override
    public Projet findById(Integer id) {
        // TODO: Implement findById functionality
        return null;
    }
}
