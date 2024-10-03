package Projet_Calbo.repositories.ProjetRepositoryImpl;

import Projet_Calbo.config.DatabaseConnection;
import Projet_Calbo.model.Tache;
import Projet_Calbo.model.Projet;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.PrioriteEnum;
import Projet_Calbo.model.Statut;
import Projet_Calbo.repositories.GeneralInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TacheImpl implements GeneralInterface<Tache> {
    private static final Logger LOGGER = Logger.getLogger(TacheImpl.class.getName());

    @Override
    public boolean save(Tache entity) {
        String sql = "INSERT INTO Tache (titre, description, priorite, statut, dateCreation, dateEcheance, projet_id, membre_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, entity.getTitre());
            pstmt.setString(2, entity.getDescription());
            pstmt.setString(3, entity.getPriorite().name());
            pstmt.setString(4, entity.getStatut().name());
            pstmt.setDate(5, Date.valueOf(entity.getDateCreation()));
            pstmt.setDate(6, entity.getDateEcheance() != null ? Date.valueOf(entity.getDateEcheance()) : null);
            pstmt.setInt(7, entity.getProjet().getId());
            pstmt.setObject(8, entity.getMembre() != null ? entity.getMembre().getId() : null);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving task", e);
        }
        return false;
    }

    @Override
    public void update(Tache entity) {
        String sql = "UPDATE Tache SET titre = ?, description = ?, priorite = ?, statut = ?, dateCreation = ?, dateEcheance = ?, projet_id = ?, membre_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entity.getTitre());
            pstmt.setString(2, entity.getDescription());
            pstmt.setString(3, entity.getPriorite().name());
            pstmt.setString(4, entity.getStatut().name());
            pstmt.setDate(5, Date.valueOf(entity.getDateCreation()));
            pstmt.setDate(6, entity.getDateEcheance() != null ? Date.valueOf(entity.getDateEcheance()) : null);
            pstmt.setInt(7, entity.getProjet().getId());
            pstmt.setObject(8, entity.getMembre() != null ? entity.getMembre().getId() : null);
            pstmt.setInt(9, entity.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating task", e);
        }
    }

    @Override
    public void delete(Tache entity) {
        String sql = "DELETE FROM Tache WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, entity.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting task", e);
        }
    }

    @Override
    public List<Tache> getAll() {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT t.*, p.nom as projet_nom, m.nom as membre_nom, m.prenom as membre_prenom FROM Tache t JOIN Projet p ON t.projet_id = p.id JOIN Membre m ON t.membre_id = m.id";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                taches.add(extractTacheFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all tasks", e);
        }
        return taches;
    }

    @Override
    public Tache findById(Integer id) {
        String sql = "SELECT t.*, p.nom as projet_nom, m.nom as membre_nom, m.prenom as membre_prenom FROM Tache t JOIN Projet p ON t.projet_id = p.id JOIN Membre m ON t.membre_id = m.id WHERE t.id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractTacheFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding task by ID", e);
        }
        return null;
    }

    public List<Tache> getTachesByProjet(int projetId) {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT t.*, p.nom as projet_nom, m.nom as membre_nom, m.prenom as membre_prenom FROM Tache t JOIN Projet p ON t.projet_id = p.id JOIN Membre m ON t.membre_id = m.id WHERE t.projet_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projetId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    taches.add(extractTacheFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting tasks by project", e);
        }
        return taches;
    }

    private Tache extractTacheFromResultSet(ResultSet rs) throws SQLException {
        Tache tache = new Tache();
        tache.setId(rs.getInt("id"));
        tache.setTitre(rs.getString("titre"));
        tache.setDescription(rs.getString("description"));
        tache.setPriorite(PrioriteEnum.valueOf(rs.getString("priorite")));
        tache.setStatut(Statut.valueOf(rs.getString("statut")));
        tache.setDateCreation(rs.getDate("dateCreation").toLocalDate());
        Date dateEcheance = rs.getDate("dateEcheance");
        if (dateEcheance != null) {
            tache.setDateEcheance(dateEcheance.toLocalDate());
        }

        Projet projet = new Projet();
        projet.setId(rs.getInt("projet_id"));
        projet.setNom(rs.getString("projet_nom"));
        tache.setProjet(projet);

        int membreId = rs.getInt("membre_id");
        if (!rs.wasNull()) {
            Members membre = new Members();
            membre.setId(membreId);
            membre.setNom(rs.getString("membre_nom"));
            membre.setPrenom(rs.getString("membre_prenom"));
            tache.setMembre(membre);
        }

        return tache;
    }

    @Override
    public List<Tache> getPage(int page, int pageSize) {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT t.*, p.nom as projet_nom, m.nom as membre_nom, m.prenom as membre_prenom " +
                "FROM Tache t " +
                "JOIN Projet p ON t.projet_id = p.id " +
                "JOIN Membre m ON t.membre_id = m.id " +
                "LIMIT ? OFFSET ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pageSize);
            pstmt.setInt(2, (page - 1) * pageSize);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    taches.add(extractTacheFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting page of tasks", e);
        }
        return taches;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM Tache";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error counting tasks", e);
        }
        return 0;
    }
}