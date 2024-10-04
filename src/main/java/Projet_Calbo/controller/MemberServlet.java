package Projet_Calbo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.model.Members;
import Projet_Calbo.model.Role;
import Projet_Calbo.services.EquipeService;
import Projet_Calbo.services.MemberService;

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	private EquipeService equipeService;

	public MemberServlet() {
		super();
		memberService = new MemberService();
		equipeService = new EquipeService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "list";
		}

		switch (action) {
			case "list":
				listMembers(request, response);
				break;
			case "add":
				showAddForm(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "delete":
				deleteMember(request, response);
				break;
			default:
				listMembers(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
			case "add":
				addMember(request, response);
				break;
			case "edit":
				updateMember(request, response);
				break;
			default:
				listMembers(request, response);
		}
	}

	private void listMembers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int page = 1;
			int pageSize = 4;

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			List<Members> members = memberService.getMemberPage(page, pageSize);
			int totalPages = memberService.getTotalPages(pageSize);

			System.out.println("Number of members retrieved: " + members.size());
			System.out.println("Total pages: " + totalPages);

			request.setAttribute("members", members);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);

			request.setAttribute("membersEmpty", members.isEmpty());

			List<Equipe> equipes = equipeService.getAll();
			request.setAttribute("equipes", equipes);
			request.setAttribute("roles", Role.values());

			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage",
					"Une erreur s'est produite lors de la récupération des membres: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Equipe> equipes = equipeService.getAll();
		request.setAttribute("equipes", equipes);
		request.setAttribute("roles", Role.values());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Members membre = new Members();
		membre.setId(id);
		if (membre != null) {
			List<Equipe> equipes = equipeService.getAll();
			request.setAttribute("member", membre);
			request.setAttribute("equipes", equipes);
			request.setAttribute("roles", Role.values());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void addMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		Role role = Role.valueOf(request.getParameter("role"));
		int equipeId = Integer.parseInt(request.getParameter("equipeId"));

		Members newMember = new Members();
		newMember.setNom(nom);
		newMember.setPrenom(prenom);
		newMember.setEmail(email);
		newMember.setRole(role);

		Equipe equipe = new Equipe();
		equipe.setId(equipeId);
		newMember.setEquipe(equipe);

		memberService.saveMember(newMember);
		response.sendRedirect(request.getContextPath() + "/member?action=list");
	}

	private void updateMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		Role role = Role.valueOf(request.getParameter("role"));
		int equipeId = Integer.parseInt(request.getParameter("equipeId"));

		Members member = new Members();
		member.setId(id);
		member.setNom(nom);
		member.setPrenom(prenom);
		member.setEmail(email);
		member.setRole(role);

		Equipe equipe = new Equipe();
		equipe.setId(equipeId);
		member.setEquipe(equipe);

		memberService.updateMember(member);

		response.sendRedirect(request.getContextPath() + "/member?action=list");
	}

	private void deleteMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Members member = new Members();
		member.setId(id);
		memberService.deleteMember(member);
		response.sendRedirect(request.getContextPath() + "/member?action=list");
	}
}
