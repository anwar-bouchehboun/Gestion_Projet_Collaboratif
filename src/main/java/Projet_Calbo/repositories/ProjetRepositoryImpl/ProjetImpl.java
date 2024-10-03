package Projet_Calbo.repositories.ProjetRepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Projet_Calbo.config.DatabaseConnection;
import Projet_Calbo.model.Equipe;
import Projet_Calbo.model.Projet;
import Projet_Calbo.model.StatutProjet;
import Projet_Calbo.repositories.GeneralInterface;

public class ProjetImpl implements GeneralInterface<Projet> {

    @Override
    public boolean save(Projet entity) {
        String sql = "INSERT INTO projet (nom, description, dateDebut, dateFin, statut, equipet_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getNom());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, Date.valueOf(entity.getDateDebut()));
            statement.setDate(4, entity.getDateFin() != null ? Date.valueOf(entity.getDateFin()) : null);
            statement.setObject(5, entity.getStatut() != null ? entity.getStatut().name() : null);
            statement.setObject(6, entity.getEquipe() != null ? entity.getEquipe().getId() : null);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(Projet entity) {
        String sql = "UPDATE projet SET nom = ?, description = ?, dateDebut = ?, dateFin = ?, statut = ?, equipet_id = ? WHERE id = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getNom());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, Date.valueOf(entity.getDateDebut()));
            statement.setDate(4, entity.getDateFin() != null ? Date.valueOf(entity.getDateFin()) : null);
            statement.setObject(5, entity.getStatut().name());
            statement.setInt(6, entity.getEquipe().getId());
            statement.setInt(7, entity.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Projet mis à jour avec succès.");
            } else {
                System.out.println("Aucun projet trouvé avec l'ID donné.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du projet: " + e.getMessage());
        }
    }

    @Override
    public void delete(Projet entity) {
        String sql = "DELETE FROM projet WHERE id = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setInt(1, entity.getId());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Projet supprimé avec succès.");
            } else {
                System.out.println("Aucun projet trouvé avec l'ID donné.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du projet: " + e.getMessage());
        }
    }

    @Override
    public List<Projet> getAll() {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM projet";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Projet projet = new Projet();
                projet.setId(resultSet.getInt("id"));
                projet.setNom(resultSet.getString("nom"));
                projet.setDescription(resultSet.getString("description"));
                projet.setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
                projet.setDateFin(
                        resultSet.getDate("dateFin") != null ? resultSet.getDate("dateFin").toLocalDate() : null);
                projet.setStatut(StatutProjet.valueOf(resultSet.getString("statut")));

                Equipe equipe = new Equipe();
                equipe.setId(resultSet.getInt("equipet_id"));
                projet.setEquipe(equipe);

                projets.add(projet);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des projets: " + e.getMessage());
        }
        return projets;
    }

    @Override
    public Projet findById(Integer id) {
        String sql = "SELECT * FROM projet WHERE id = ?";
        Projet projet = null;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                projet = new Projet();
                projet.setId(resultSet.getInt("id"));
                projet.setNom(resultSet.getString("nom"));
                projet.setDescription(resultSet.getString("description"));
                projet.setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
                projet.setDateFin(
                        resultSet.getDate("dateFin") != null ? resultSet.getDate("dateFin").toLocalDate() : null);
                projet.setStatut(StatutProjet.valueOf(resultSet.getNString("statut")));
                Equipe equipe = new Equipe();
                equipe.setId(resultSet.getInt("equipet_id"));
                projet.setEquipe(equipe);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du projet: " + e.getMessage());
        }
        return projet;
    }

    @Override
    public List<Projet> getPage(int page, int pageSize) {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM projet LIMIT ? OFFSET ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setInt(1, pageSize);
            statement.setInt(2, (page - 1) * pageSize);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Projet projet = new Projet();
                    projet.setId(resultSet.getInt("id"));
                    projet.setNom(resultSet.getString("nom"));
                    projet.setDescription(resultSet.getString("description"));
                    projet.setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
                    projet.setDateFin(
                            resultSet.getDate("dateFin") != null ? resultSet.getDate("dateFin").toLocalDate() : null);
                    projet.setStatut(StatutProjet.valueOf(resultSet.getString("statut")));

                    Equipe equipe = new Equipe();
                    equipe.setId(resultSet.getInt("equipet_id"));
                    projet.setEquipe(equipe);

                    projets.add(projet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la page de projets: " + e.getMessage());
        }
        return projets;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM projet";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du comptage des projets: " + e.getMessage());
        }
        return 0;
    }
}
