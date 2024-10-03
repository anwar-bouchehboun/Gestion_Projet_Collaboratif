package Projet_Calbo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        // System.out.println(tache.getId() + ": " + tache.getTitre());
        // }

        // // Assert that we have at least one task in the database
        // assertTrue(allTaches.size() > 0);

        // // Verify that each task has valid data
        // for (Tache tache : allTaches) {
        // assertNotNull(tache.getId());
        // assertNotNull(tache.getTitre());
        // assertNotNull(tache.getDescription());
        // assertNotNull(tache.getPriorite());
        // assertNotNull(tache.getStatut());
        // assertNotNull(tache.getDateCreation());
        // assertNotNull(tache.getProjet());
        // }
    }

    @Test
    public void testGetMembersAndTasksForProject() {
        int projectId = 1; // Assuming project with ID 1 exists
        Projet projet = new Projet();
        projet.setId(projectId);
        Map<Members, List<Tache>> memberTaskMap = tacheService.getMembersAndTasksForProject(projet);
    
        assertNotNull(memberTaskMap);
        assertFalse(memberTaskMap.isEmpty());
    
        System.out.println("Members and Tasks for Project " + projectId + ":");
        for (Map.Entry<Members, List<Tache>> entry : memberTaskMap.entrySet()) {
            Members member = entry.getKey();
            List<Tache> tasks = entry.getValue();
    
            System.out.println("Member: " + member.getNom() + " " + member.getPrenom() + " (ID: " + member.getId() + ")");
            System.out.println("Tasks:");
            if (tasks.isEmpty()) {
                System.out.println("No tasks assigned");
            } else {
                for (Tache task : tasks) {
                    System.out.println("- " + task.getTitre() + " (ID: " + task.getId() + ", Priority: " + task.getPriorite() + ")");
                }
            }
            System.out.println();
        }
    
        // Print all tasks for the project
        List<Tache> allProjectTasks = tacheService.getTachesByProjet(projectId);
        System.out.println("All tasks for Project " + projectId + ":");
        for (Tache task : allProjectTasks) {
            System.out.println("- " + task.getTitre() + " (ID: " + task.getId() + ", Member ID: " + (task.getMembre() != null ? task.getMembre().getId() : "null") + ")");
        }
    
        // Assert that at least one member has tasks
        boolean atLeastOneMemberHasTasks = memberTaskMap.values().stream().anyMatch(tasks -> !tasks.isEmpty());
        assertTrue("At least one member should have tasks", atLeastOneMemberHasTasks);
    }
}