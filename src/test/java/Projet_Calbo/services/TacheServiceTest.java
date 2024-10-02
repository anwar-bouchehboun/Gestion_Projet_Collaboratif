package Projet_Calbo.services;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Projet_Calbo.model.Tache;
import Projet_Calbo.model.Projet;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.PrioriteEnum;
import Projet_Calbo.model.Statut;

import java.time.LocalDate;

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
}