package Projet_Calbo.repositories.ProjetRepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Projet_Calbo.config.DatabaseConnection;
import Projet_Calbo.model.Equipe;
import Projet_Calbo.repositories.GeneralInterface;

public class EquipeImpl implements GeneralInterface<Equipe>  {

	@Override
	public boolean save(Equipe entity) {
	    String sql = "INSERT INTO equipe (nom) VALUES (?)";
	    
	    try (
	        PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) { 
	        
	        statement.setString(1, entity.getNom());
	        
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            return true; // Success
	        }
	    } catch (SQLException e) {
	        System.out.println("Error inserting team: " + e.getMessage());
	    }
	    return false;
	}


	@Override
	public void update(Equipe entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Equipe entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Equipe> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Equipe findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
