<<<<<<< HEAD:src/test/java/Projet_Calbo/services/MemberServiceTest.java
package Projet_Calbo.services;

=======
package Projet_Calbo.repositories.ProjetRepositoryImpl;
>>>>>>> 10a518f049d9c32be4040eaec055cdcc7740b1d6:src/test/java/Projet_Calbo/repositories/ProjetRepositoryImpl/MemberImpTest.java

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.Role;

public class MemberServiceTest {
	
	private MemberService memberService;


	@Before
	public void setUp() throws Exception {
		memberService = new MemberService();

	}

	@Test
	public void testGetAll() {
		List<Members> membersList = memberService.getAll();
		 membersList.stream().forEach(System.out::println);
	}

	@Test
	public void testSaveEquipe() {
		Members member;
		Equipe e;
		member = new Members();
		e = new Equipe();
		member.setNom("yassir");
		member.setPrenom("bouchehboun");
		member.setEmail("a.aer@gmail.com");
		member.setRole(Role.DEVELOPPEUR);
		e.setId(2);
		member.setEquipe(e);
		memberService.saveEquipe(member);
	}

	@Test
	public void testDeleteEquipe() {
		  Members member = new Members();
		    member.setId(6);
		    memberService.deleteEquipe(member);
	}

	@Test
	public void testUpdateEquipe() {
		   Members member = new Members();
		    member.setId(10);
		    member.setNom("anwar");
		    member.setPrenom("bouchehboun");
		    member.setEmail("a.ab95@gmail.com");
		    member.setRole(Role.DESIGNER);

		    Equipe e = new Equipe();
		    e.setId(16);
		    member.setEquipe(e);
		    memberService.updateEquipe(member);
	}

}
