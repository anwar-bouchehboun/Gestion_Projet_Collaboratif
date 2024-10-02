package Projet_Calbo.repositories.ProjetRepositoryImpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.Role;
import Projet_Calbo.services.MemberService;

public class MemberImpTest {

	private MemberService memberService;

	@Before
	public void setUp() throws Exception {
		
		memberService = new MemberService();
	}

	@Test
	public void testSave() {
		Members member;
		Equipe e;
		member = new Members();
		e = new Equipe();
		member.setNom("yassir");
		member.setPrenom("bouchehboun");
		member.setEmail("a.ab@gmail.com");
		member.setRole(Role.DEVELOPPEUR);
		e.setId(2);
		member.setEquipe(e);
		memberService.saveEquipe(member);
	}

	@Test
	public void testUpdate() {
		   Members member = new Members();
		    member.setId(5);
		    member.setNom("anwar");
		    member.setPrenom("bouchehboun");
		    member.setEmail("a.ab95@gmail.com");
		    member.setRole(Role.DESIGNER);

		    Equipe e = new Equipe();
		    e.setId(16);
		    member.setEquipe(e);
		    memberService.updateEquipe(member);

	}

	@Test
	public void testDelete() {
		   Members member = new Members();
		    member.setId(5);
		    memberService.deleteEquipe(member);

		
	}

	@Test
	public void testGetAll() {
		
		 List<Members> membersList = memberService.getAll();
		 membersList.stream().forEach(System.out::println);
	}

}
