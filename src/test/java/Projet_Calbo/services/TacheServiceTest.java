package Projet_Calbo.services;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import Projet_Calbo.model.Members;
import Projet_Calbo.model.PrioriteEnum;
import Projet_Calbo.model.Projet;
import Projet_Calbo.model.Statut;
import Projet_Calbo.model.Tache;

public class TacheServiceTest {

    private TacheService tacheService;

    @Before
    public void setUp() {
        tacheService = new TacheService();
    }

    @Test
    public void testAddTache() {
        Tache tache = createSampleTache();
        boolean result = tacheService.addTache(tache);
        assertTrue(result);
        assertNotEquals(0, tache.getId());
    }

    @Test
    public void testUpdateTache() {
        Tache tache = createSampleTache();
        tacheService.addTache(tache);

        tache.setTitre("Updated Task");
        tacheService.updateTache(tache);

        Tache updatedTache = tacheService.getTacheById(tache.getId());
        assertEquals("Updated Task", updatedTache.getTitre());
    }

    @Test
    public void testDeleteTache() {
        Tache tache = createSampleTache();
        tacheService.addTache(tache);

        tacheService.deleteTache(tache);

        Tache deletedTache = tacheService.getTacheById(tache.getId());
        assertNull(deletedTache);
    }

    private Tache createSampleTache() {
        Tache tache = new Tache();
        tache.setTitre("Sample Task");
        tache.setDescription("This is a sample task");
        tache.setPriorite(PrioriteEnum.MOYENNE);
        tache.setStatut(Statut.AFAIRE);
        tache.setDateCreation(LocalDate.now());
        tache.setDateEcheance(LocalDate.now().plusDays(7));

        Projet projet = new Projet();
        projet.setId(1);
        tache.setProjet(projet);

        Members membre = new Members();
        membre.setId(1);
        tache.setMembre(membre);

        return tache;
    }

    @Test
    public void testGetAllTaches() {
        // Get all tasks from the database
        List<Tache> allTaches = tacheService.getAllTaches();

        // Assert that the list is not null
        assertNotNull(allTaches);

        // // Print out all tasks for debugging
        // System.out.println("All tasks:");
        // for (Tache tache : allTaches) {
        //     System.out.println(tache.getId() + ": " + tache.getTitre());
        // }

        // // Assert that we have at least one task in the database
        // assertTrue(allTaches.size() > 0);

        // // Verify that each task has valid data
        // for (Tache tache : allTaches) {
        //     assertNotNull(tache.getId());
        //     assertNotNull(tache.getTitre());
        //     assertNotNull(tache.getDescription());
        //     assertNotNull(tache.getPriorite());
        //     assertNotNull(tache.getStatut());
        //     assertNotNull(tache.getDateCreation());
        //     assertNotNull(tache.getProjet());
        // }
    }

    @Test
    public void testPagination() {
        int pageSize = 5;
        int totalPages = tacheService.getTotalPages(pageSize);
    
        // Assert that we have at least 2 pages
        assertTrue(totalPages >= 2);
    
        // Test first page
        List<Tache> firstPage = tacheService.getTachePage(1, pageSize);
        assertEquals(pageSize, firstPage.size());
    
        // Test second page
        List<Tache> secondPage = tacheService.getTachePage(2, pageSize);
        assertTrue(secondPage.size() > 0);
        assertTrue(secondPage.size() <= pageSize);
    
        // Ensure first and second page are different
        assertNotEquals(firstPage.get(0).getId(), secondPage.get(0).getId());
    
        // Test last page
        List<Tache> lastPage = tacheService.getTachePage(totalPages, pageSize);
        assertTrue(lastPage.size() > 0);
        assertTrue(lastPage.size() <= pageSize);
    
        // Test out of range page (should return empty list)
        List<Tache> outOfRangePage = tacheService.getTachePage(totalPages + 1, pageSize);
        assertTrue(outOfRangePage.isEmpty());
    }
}