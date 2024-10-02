package Projet_Calbo.repositories.ProjetRepositoryImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Projet_Calbo.config.DatabaseConnection;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.PrioriteEnum;
import Projet_Calbo.model.Projet;
import Projet_Calbo.model.Statut;
import Projet_Calbo.model.Tache;
import Projet_Calbo.repositories.GeneralInterface;

public class TacheImpl implements GeneralInterface<Tache> {

    @Override
    public boolean save(Tache entity) {
        String sql = "INSERT INTO tache (titre, description, priorite, statut, date_creation, date_echeance, projet_id, membre_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getTitre());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getPriorite().name());
            statement.setString(4, entity.getStatut().name());
            statement.setDate(5, Date.valueOf(entity.getDateCreation()));
            statement.setDate(6, Date.valueOf(entity.getDateEcheance()));
            statement.setInt(7, entity.getProjet().getId());
            statement.setInt(8, entity.getMembre().getId());
            
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Tache entity) {
        String sql = "UPDATE tache SET titre = ?, description = ?, priorite = ?, statut = ?, date_creation = ?, date_echeance = ?, projet_id = ?, membre_id = ? WHERE id = ?";
        
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getTitre());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getPriorite().name());
            statement.setString(4, entity.getStatut().name());
            statement.setDate(5, Date.valueOf(entity.getDateCreation()));
            statement.setDate(6, Date.valueOf(entity.getDateEcheance()));
            statement.setInt(7, entity.getProjet().getId());
            statement.setInt(8, entity.getMembre().getId());
            statement.setInt(9, entity.getId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Tache entity) {
        String sql = "DELETE FROM tache WHERE id = ?";
        
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tache> getAll() {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache";
        
        try (Statement statement = DatabaseConnection.getInstance().getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                taches.add(extractTacheFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taches;
    }

    @Override
    public Tache findById(Integer id) {
        String sql = "SELECT * FROM tache WHERE id = ?";
        
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractTacheFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Tache extractTacheFromResultSet(ResultSet resultSet) throws SQLException {
        Tache tache = new Tache();
        tache.setId(resultSet.getInt("id"));
        tache.setTitre(resultSet.getString("titre"));
        tache.setDescription(resultSet.getString("description"));
        tache.setPriorite(PrioriteEnum.valueOf(resultSet.getString("priorite")));
        tache.setStatut(Statut.valueOf(resultSet.getString("statut")));
        tache.setDateCreation(resultSet.getDate("date_creation").toLocalDate());
        tache.setDateEcheance(resultSet.getDate("date_echeance").toLocalDate());
        
        Projet projet = new Projet();
        projet.setId(resultSet.getInt("projet_id"));
        Members membre = new Members();
        membre.setId(resultSet.getInt("membre_id"));
        tache.setProjet(projet);
        tache.setMembre(membre);
        
        return tache;
    }
    public List<Tache> getTachesByProjet(int projetId) {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE projet_id = ?";
        
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setInt(1, projetId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    taches.add(extractTacheFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taches;
    }
}
