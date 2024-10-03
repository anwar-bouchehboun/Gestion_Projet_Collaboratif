package Projet_Calbo.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.Role;
import Projet_Calbo.utilis.LoggerMessage;

public class MemberServiceTest {
	
	private MemberService memberService;


	@Before
	public void setUp() throws Exception {
		
		memberService = new MemberService();


	}

	@Test
	public void testGetAll() {
		//fetch data
		List<Members> membersList = memberService.getAll();
		 membersList.stream().forEach(System.out::println);
	        LoggerMessage.info("All Data");

	}

	@Test
	public void testSaveEquipe() {
		//create 
		Members member;
		Equipe e;
		member = new Members();
		e = new Equipe();
		member.setNom("yassir");
		member.setPrenom("bouchehboun");
		member.setEmail("a.a@gmail.com");
		member.setRole(Role.DEVELOPPEUR);
		e.setId(2);
		member.setEquipe(e);
		memberService.saveEquipe(member);
		LoggerMessage.info("save Members");
	}

	@Test
	public void testDeleteEquipe() {
		//delete data
		  Members member = new Members();
		    member.setId(6);
		    memberService.deleteEquipe(member);
			LoggerMessage.info("delete Members");

	}

	@Test
	public void testUpdateEquipe() {
		//update data 
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
			LoggerMessage.info("update Members");

	}

}
