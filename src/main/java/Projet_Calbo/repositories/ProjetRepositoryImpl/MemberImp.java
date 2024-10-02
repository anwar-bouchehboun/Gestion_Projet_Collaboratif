package Projet_Calbo.repositories.ProjetRepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Projet_Calbo.config.DatabaseConnection;
import Projet_Calbo.model.Equipe;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.Role;
import Projet_Calbo.repositories.GeneralInterface;

public class MemberImp implements GeneralInterface<Members> {

	@Override
	public boolean save(Members entity) {
	    String sql = "INSERT INTO membre (nom, prenom, email, role,equipe_id) VALUES (?, ?, ?, ?, ?)";
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
	        
	        statement.setString(1, entity.getNom());
	        statement.setString(2, entity.getPrenom());
	        statement.setString(3, entity.getEmail());
	        statement.setObject(4, entity.getRole().name());
	        statement.setInt(5, entity.getEquipe().getId()); 
	        
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            return true; 
	        }
	    } catch (SQLException e) {
	        System.out.println("Error inserting member: " + e.getMessage());
	    }
	    
	    return false;
	}


	@Override
	public void update(Members entity) {
	    String sql = "UPDATE membre SET nom = ?, prenom = ?, email = ?, role = ?, equipe_id = ? WHERE id = ?";
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
	        
	        statement.setString(1, entity.getNom());
	        statement.setString(2, entity.getPrenom());
	        statement.setString(3, entity.getEmail());
	        statement.setObject(4, entity.getRole().name());
	        statement.setInt(5, entity.getEquipe().getId()); 
	        statement.setInt(6, entity.getId()); 
	        
	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Member updated successfully.");
	        } else {
	            System.out.println("No member found with the given ID.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating member: " + e.getMessage());
	    }
	}


	@Override
	public void delete(Members entity) {
	    String sql = "DELETE FROM membre WHERE id = ?";
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
	        
	        // Set the ID of the member to delete
	        statement.setInt(1, entity.getId());
	        
	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Member deleted successfully.");
	        } else {
	            System.out.println("No member found with the given ID.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error deleting member: " + e.getMessage());
	    }
	}


	@Override
	public List<Members> getAll() {
	    List<Members> membersList = new ArrayList<>();
	    // Using an alias for equipe.nom to avoid confusion in the ResultSet
	    String sql = "SELECT membre.id, membre.nom AS membre_nom, membre.prenom, membre.email, membre.role, equipe.nom AS equipe_nom " +
	                 "FROM membre JOIN equipe ON membre.equipe_id = equipe.id"; 

	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
	        ResultSet resultSet = statement.executeQuery()) {
	        
	        while (resultSet.next()) {
	            Members member = new Members();
	            member.setId(resultSet.getInt("id"));
	            member.setNom(resultSet.getString("membre_nom")); 
	            member.setPrenom(resultSet.getString("prenom"));
	            member.setEmail(resultSet.getString("email"));
	            member.setRole(Role.valueOf(resultSet.getString("role")));
	            
	            Equipe equipe = new Equipe();
	            equipe.setNom(resultSet.getString("equipe_nom")); 
	            member.setEquipe(equipe); 
	            
	            membersList.add(member); 
	        }
	    } catch (SQLException e) {
	        System.out.println("Error retrieving members: " + e.getMessage());
	    }

	    return membersList;
	}



	@Override
	public Members findById(Integer id) {
	    Members member = null; // Initialize member to null
	    String sql = "SELECT membre.id, membre.nom, membre.prenom, membre.email, membre.role, membre.equipe_id, equipe.nom AS equipe_nom " +
	                 "FROM membre LEFT JOIN equipe ON membre.equipe_id = equipe.id WHERE membre.id = ?"; // Using LEFT JOIN to get the equipe info

	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
	        
	        statement.setInt(1, id); 
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                member = new Members();
	                member.setId(resultSet.getInt("id"));
	                member.setNom(resultSet.getString("nom"));
	                member.setPrenom(resultSet.getString("prenom"));
	                member.setEmail(resultSet.getString("email"));
	                member.setRole(Role.valueOf(resultSet.getString("role")));
	                
	                Equipe equipe = new Equipe();
	                equipe.setNom(resultSet.getString("equipe_nom"));
	                member.setEquipe(equipe);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error retrieving member by ID: " + e.getMessage());
	    }

	    return member; 
	}

	
	

}
