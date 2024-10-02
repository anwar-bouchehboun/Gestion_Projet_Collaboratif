package Projet_Calbo.repositories.ProjetRepositoryImpl;

import org.junit.Before;
import org.junit.Test;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.services.EquipeService;


public class EquipeImplTest {
    private EquipeService equipeService;

    @Before
    public void setUp() throws Exception {
        equipeService = new EquipeService();
    }

    @Test
    public void testSave() {
        Equipe equipe = new Equipe();
        equipe.setNom("Team C");
        
        equipeService.saveEquipe(equipe); 
      
    }

    @Test
    public void testUpdate() {
        Equipe equipe = new Equipe();
        equipe.setId(2);
        equipe.setNom("Updated Team A");
        equipeService.updateEquipe(equipe); 
        
      
    }
    @Test
    public void testGetAll() {
      
      equipeService.getAll(); 
        
       
    }

    @Test
    public void testFindById() {
        Equipe equipe = new Equipe();
        equipe.setId(1);
        
       equipeService.equipe.findById(equipe.getId()); 
     
    }
    
    @Test
    public void testDelete() {
        Equipe equipe = new Equipe();
       equipe.setId(15);
        
        equipeService.deleteEquipe(equipe);
        
       
    }

 

  
}
