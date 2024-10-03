package Projet_Calbo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.services.EquipeService;
import Projet_Calbo.utilis.LoggerMessage;

public class EquipeServlet extends HttpServlet {
	private EquipeService equipeService;

	public EquipeServlet() {
		super();
		equipeService = new EquipeService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
				List<Equipe> equipeList = equipeService.getAll();
			
				  request.setAttribute("equipeList", equipeList);
	
		} catch (Exception e) {
			LoggerMessage.error(e.getMessage());
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/equipe.jsp");
			dispatcher.forward(request, response);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/equipe.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String idParam = request.getParameter("id");
		System.out.println(idParam);
		try {
			if ("addTeam".equals(action)) {
				String teamName = request.getParameter("teamName");
				Equipe newEquipe = new Equipe();
				newEquipe.setNom(teamName);
				equipeService.saveEquipe(newEquipe);
				request.setAttribute("message", "Equipe Ajouter avec succès !");
				doGet(request, response);

			} else if ("deleteTeam".equals(action)) {
				int id = Integer.parseInt(idParam);
				Equipe equipeToDelete = new Equipe();
				equipeToDelete.setId(id);
				equipeService.deleteEquipe(equipeToDelete);
				request.setAttribute("message", "Equipe supprimer avec succès !");
				doGet(request, response);
			} else if ("updateTeam".equals(action)) {

				int id = Integer.parseInt(idParam);
				String newName = request.getParameter("newName");
				Equipe equipeToUpdate = new Equipe();
				equipeToUpdate.setId(id);
				equipeToUpdate.setNom(newName);
				equipeService.updateEquipe(equipeToUpdate);
				request.setAttribute("message", "Equipe Modifier avec succès !");
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "An error occurred while processing the request.");
			doGet(request, response);
		}
	}

	protected void listEquipes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		int pageSize = 5; 

		String pageParam = request.getParameter("page");
		if (pageParam != null && !pageParam.isEmpty()) {
			page = Integer.parseInt(pageParam);
		}

		List<Equipe> equipes = equipeService.getEquipePage(page, pageSize);
		 
		long totalEquipes = equipeService.countEquipes();
		int totalPages = (int) Math.ceil((double) totalEquipes / pageSize);
        
		request.setAttribute("equipes", equipes);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/equipe.jsp");
		dispatcher.forward(request, response);
	}

}