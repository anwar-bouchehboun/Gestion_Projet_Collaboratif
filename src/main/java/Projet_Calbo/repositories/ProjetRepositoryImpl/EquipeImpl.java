package Projet_Calbo.repositories.ProjetRepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Projet_Calbo.config.DatabaseConnection;
import Projet_Calbo.model.Equipe;
import Projet_Calbo.repositories.GeneralInterface;
import Projet_Calbo.repositories.MultiInterface;
import Projet_Calbo.utilis.LoggerMessage;
public class EquipeImpl implements GeneralInterface<Equipe> , MultiInterface<Equipe> {

	@Override
	public boolean save(Equipe entity) {
	    String sql = "INSERT INTO equipe (nom) VALUES (?)";
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) { 
	        
	        statement.setString(1, entity.getNom());
	        
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            return true; 
	        }
	    } catch (SQLException e) {
            LoggerMessage.error("Error inserting team: " + e.getMessage());
	    }
	    return false;
	}



	@Override
	public void update(Equipe entity) {
	    String sql = "UPDATE equipe SET nom = ? WHERE id = ?";
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
	        
	        statement.setString(1, entity.getNom());
	        statement.setInt(2, entity.getId());
	        
	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	        	LoggerMessage.info("Team updated successfully.");
	        } else {
	        	LoggerMessage.warn("No team found with the given ID.");
	        }
	    } catch (SQLException e) {
            LoggerMessage.error("Error updating team: " + e.getMessage());

	    }
	}

	@Override
	public void delete(Equipe entity) {
	    String sql = "DELETE FROM equipe WHERE id = ?";
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
	        
	        statement.setInt(1, entity.getId());
	        
	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Team deleted successfully.");
	        } else {
	        	LoggerMessage.warn("No team found with the given ID.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error deleting team: " + e.getMessage());
	    }
	}


	@Override
	public List<Equipe> getAll() {
	    List<Equipe> equipes = new ArrayList<>();
	    String sql = "SELECT * FROM equipe";
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
	        ResultSet resultSet = statement.executeQuery()) {
	        
	        while (resultSet.next()) {
	            Equipe equipe = new Equipe();
	            equipe.setId(resultSet.getInt("id"));
	            equipe.setNom(resultSet.getString("nom"));
	            equipes.add(equipe);
	        }
	    } catch (SQLException e) {
	    	LoggerMessage.error("Error retrieving teams: " + e.getMessage());
	    }
	    
	    return equipes;
	}


	@Override
	public Equipe findById(Integer id) {
	    String sql = "SELECT * FROM equipe WHERE id = ?";
	    Equipe equipe = null;
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
	        
	        statement.setInt(1, id);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                equipe = new Equipe();
	                equipe.setId(resultSet.getInt("id"));
	                equipe.setNom(resultSet.getString("nom"));
	            }
	        }
	    } catch (SQLException e) {
	    	LoggerMessage.error("Error finding team by ID: " + e.getMessage());
	    }
	    
	    return equipe;
	}
	@Override
	public List<Equipe> getPage(int page, int pageSize) {
		List<Equipe> equipes = new ArrayList<>();
		String sql = "SELECT * FROM equipe LIMIT ? OFFSET ?";
		
		try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
			statement.setInt(1, pageSize);
			statement.setInt(2, (page - 1) * pageSize);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Equipe equipe = new Equipe();
					equipe.setId(resultSet.getInt("id"));
					equipe.setNom(resultSet.getString("nom"));
					equipes.add(equipe);
				}
			}
		} catch (SQLException e) {
			LoggerMessage.error("Error retrieving page of teams: " + e.getMessage());
		}
		
		return equipes;
	}
	
	@Override
	public long count() {
		String sql = "SELECT COUNT(*) FROM equipe";
		long count = 0;
		
		try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				count = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			LoggerMessage.error("Error counting teams: " + e.getMessage());
		}
		
		return count;
	}



	@Override
	public List<Equipe> findByName(String name) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByName'");
	}

}
