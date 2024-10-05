package Projet_Calbo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Projet_Calbo.model.Projet;
import Projet_Calbo.model.StatutProjet;
import Projet_Calbo.model.Equipe;
import Projet_Calbo.services.ProjetService;
import Projet_Calbo.services.EquipeService;

public class ProjetServlet extends HttpServlet {
	private ProjetService projetService;
	private EquipeService equipeService;

	public ProjetServlet() {
		super();
		projetService = new ProjetService();
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
				listProjets(request, response);
				break;
			case "add":
				showAddForm(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "delete":
				deleteProjet(request, response);
				break;
			default:
				listProjets(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
			case "add":
				addProjet(request, response);
				break;
			case "update":
				updateProjet(request, response);
				break;
			default:
				listProjets(request, response);
		}
	}

	private void listProjets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		List<Projet> projets = projetService.getProjetsPagines((page - 1) * recordsPerPage, recordsPerPage);
		long noOfRecords = projetService.getNombreTotalProjets();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		List<Equipe> equipes = equipeService.getAll();
		StatutProjet[] statuts = StatutProjet.values();

		request.setAttribute("projets", projets);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("equipes", equipes);
		request.setAttribute("statuts", statuts);

		RequestDispatcher view = request.getRequestDispatcher("/views/projet.jsp");
		view.forward(request, response);
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Equipe> equipes = equipeService.getAll();
		request.setAttribute("equipes", equipes);
		request.setAttribute("statuts", StatutProjet.values());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/projet-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Projet existingProjet = projetService.getById(id);
		List<Equipe> equipes = equipeService.getAll();
		request.setAttribute("projet", existingProjet);
		request.setAttribute("equipes", equipes);
		request.setAttribute("statuts", StatutProjet.values());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/projet-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addProjet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
		LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
		StatutProjet statut = StatutProjet.valueOf(request.getParameter("statut"));
		int equipeId = Integer.parseInt(request.getParameter("equipeId"));

		Equipe equipe = new Equipe();
		equipe.setId(equipeId);

		Projet newProjet = new Projet();
		newProjet.setNom(nom);
		newProjet.setDescription(description);
		newProjet.setDateDebut(dateDebut);
		newProjet.setDateFin(dateFin);
		newProjet.setStatut(statut);
		newProjet.setEquipe(equipe);
		projetService.saveProjet(newProjet);

		response.sendRedirect("projet?action=list");
	}

	private void updateProjet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
		LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
		StatutProjet statut = StatutProjet.valueOf(request.getParameter("statut"));
		int equipeId = Integer.parseInt(request.getParameter("equipeId"));

		Equipe equipe = new Equipe();
		equipe.setId(equipeId);

		Projet newProjet = new Projet();
		newProjet.setNom(nom);
		newProjet.setDescription(description);
		newProjet.setDateDebut(dateDebut);
		newProjet.setDateFin(dateFin);
		newProjet.setStatut(statut);
		newProjet.setEquipe(equipe);
		newProjet.setId(id);
		projetService.updateProjet(newProjet);

		response.sendRedirect("projet?action=list");
	}

	private void deleteProjet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Projet projet = projetService.getById(id);
		projetService.deleteProjet(projet);
		response.sendRedirect("projet?action=list");
	}
}