package Projet_Calbo.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import Projet_Calbo.model.Projet;
import Projet_Calbo.utilis.LoggerMessage;

public class ProjetServiceTest {

    private ProjetService projetService;

    @Before
    public void setUp() throws Exception {
        projetService = new ProjetService();
    }

    
    
    @Test
    public void testGetAll() {
        List<Projet> projetList = projetService.getAllProjets(); 
        projetList.forEach(System.out::println);
        LoggerMessage.info("Fetched all projects successfully.");
    }

    @Test


    public void testDeleteProjet() {
        Projet projet = new Projet();
        projet.setId(3); 
        projetService.deleteProjet(projet); 

        LoggerMessage.info("Project deleted successfully.");
    }
}
