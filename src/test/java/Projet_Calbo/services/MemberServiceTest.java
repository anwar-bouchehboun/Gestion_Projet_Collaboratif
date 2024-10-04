package Projet_Calbo.services;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.Role;
import Projet_Calbo.repositories.ProjetRepositoryImpl.MemberImp;
import Projet_Calbo.utilis.LoggerMessage;

public class MemberServiceTest {

	private MemberService memberService;

	@Before
	public void setUp() throws Exception {

		memberService = new MemberService();

	}

	@Test
	public void testGetAll() {
		// fetch data
		List<Members> membersList = memberService.getAll();
		membersList.stream().forEach(System.out::println);
		LoggerMessage.info("All Data");

	}

	@Test
	public void testSaveEquipe() {
		// create
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
		memberService.saveMember(member);
		LoggerMessage.info("save Members");
	}

	@Test
	public void testDeleteEquipe() {
		// delete data
		Members member = new Members();
		member.setId(6);
		memberService.deleteMember(member);
		LoggerMessage.info("delete Members");

	}

	@Test
	public void testUpdateEquipe() {
		// update data
		Members member = new Members();
		member.setId(10);
		member.setNom("anwar");
		member.setPrenom("bouchehboun");
		member.setEmail("a.ab95@gmail.com");
		member.setRole(Role.DESIGNER);

		Equipe e = new Equipe();
		e.setId(16);
		member.setEquipe(e);
		memberService.updateMember(member);
		LoggerMessage.info("update Members");

	}

	@Test
	public void testPagination() {
		MemberImp memberImp = new MemberImp();
		int pageSize = 5;
		int totalPages = (int) Math.ceil((double) memberImp.count() / pageSize);

		for (int page = 1; page <= totalPages; page++) {
			List<Members> members = memberImp.getPage(page, pageSize);

			assertNotNull("Members list should not be null", members);
			assertTrue("Members list size should be less than or equal to page size",
					members.size() <= pageSize);

			if (page < totalPages) {
				assertEquals("All pages except the last should have exactly pageSize members",
						pageSize, members.size());
			} else {
				assertTrue("Last page should have pageSize or fewer members",
						members.size() > 0 && members.size() <= pageSize);
			}

			// Check that each member has all required fields
			for (Members member : members) {
				assertNotNull("Member ID should not be null", member.getId());
				assertNotNull("Member name should not be null", member.getNom());
				assertNotNull("Member email should not be null", member.getEmail());
				assertNotNull("Member role should not be null", member.getRole());
				assertNotNull("Member equipe should not be null", member.getEquipe());
				assertNotNull("Member equipe ID should not be null", member.getEquipe().getId());
				assertNotNull("Member equipe name should not be null", member.getEquipe().getNom());
			}
			System.out.println("Page " + page + " validated successfully.");
		}

		// Test invalid page numbers
		List<Members> emptyList = memberImp.getPage(0, pageSize);
		assertTrue("Page 0 should return an empty list", emptyList.isEmpty());

		emptyList = memberImp.getPage(totalPages + 1, pageSize);
		assertTrue("Page beyond total pages should return an empty list", emptyList.isEmpty());
	}

}
