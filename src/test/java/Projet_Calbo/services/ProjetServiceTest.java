package Projet_Calbo.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import Projet_Calbo.model.Projet;
import Projet_Calbo.utilis.LoggerMessage;
import Projet_Calbo.model.Equipe; 
import Projet_Calbo.model.StatutProjet; 
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

public class ProjetServiceTest {

    private ProjetService projetService;

    @Before
    public void setUp() throws Exception {
        projetService = new ProjetService();
    }

    @Test
    public void testSaveProjet() {
        Projet projet = new Projet();
        projet.setNom("New Project");
        projet.setDescription("Description of the new project");
        projet.setDateDebut(java.time.LocalDate.now());
        projet.setDateFin(java.time.LocalDate.now().plusDays(30));
        projet.setStatut(StatutProjet.ENCOURS); 

        Equipe equipe = new Equipe();
        equipe.setId(2); 
        projet.setEquipe(equipe);

        boolean isSaved = projetService.saveProjet(projet);
        assertTrue("The projet should be saved successfully.", isSaved); 
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
    
    @Test
    public void testUpdateProjet() {
        Projet projet = new Projet();
        projet.setId(2); 
        projet.setNom("Project to Update");
        projet.setDescription("Description of the project to update");
        projet.setDateDebut(java.time.LocalDate.now());
        projet.setDateFin(java.time.LocalDate.now().plusDays(30));
        projet.setStatut(StatutProjet.ENCOURS); 

        Equipe equipe = new Equipe();
        equipe.setId(2); 
        projet.setEquipe(equipe);

        projetService.updateProjet(projet); 
    }
    
    @Test
    public void testGetById() {
        int projetId = 1; 
        Projet projet = projetService.getById(projetId);

        assertTrue("The projet should be retrieved successfully.", projet != null);
        System.out.println(projet); 
    }

    @Test
    public void testFindByName() {
        String searchName = "New Project"; 
        List<Projet> projets = projetService.findByName(searchName);
        
        assertNotNull("The list of projects should not be null.", projets);
        assertFalse("There should be at least one project found with the given name.", projets.isEmpty());

        projets.forEach(projet -> {
            assertTrue("Each project should contain the search term in its name.", projet.getNom().contains(searchName));
            System.out.println(projet); 
        });
    }

}
