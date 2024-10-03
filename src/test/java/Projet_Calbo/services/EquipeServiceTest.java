package Projet_Calbo.services;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Projet_Calbo.model.Equipe;

public class EquipeServiceTest {
    private EquipeService equipeService;

	@Before
	public void setUp() throws Exception {
        equipeService = new EquipeService();

	}

	@Test
	public void testEquipeService() {
	}

	@Test
	public void testGetAll() {
		List<Equipe> equipeListe = equipeService.getAll();
		equipeListe.stream().forEach(System.out::println);
	}

	@Test
	public void testSaveEquipe() {
		Equipe equipe = new Equipe();
        equipe.setNom("Team C");
        
        equipeService.saveEquipe(equipe); 
	}

	@Test
	public void testDeleteEquipe() {
	    Equipe equipe = new Equipe();
	       equipe.setId(15);
	        
	        equipeService.deleteEquipe(equipe);
	}

	@Test
	public void testUpdateEquipe() {
		  Equipe equipe = new Equipe();
	        equipe.setId(2);
	        equipe.setNom("Updated Team A");
	        equipeService.updateEquipe(equipe); 
	}

}
