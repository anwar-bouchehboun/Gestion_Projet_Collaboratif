package Projet_Calbo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Projet_Calbo.model.Equipe;
import Projet_Calbo.model.Projet;
import Projet_Calbo.model.StatutProjet;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize = 5;
        
        // Get the search parameter from the request
        String searchQuery = request.getParameter("search");

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }

        // Retrieve all projects and apply pagination
        List<Projet> projects = projetService.getPage(page, pageSize);

        // Filter projects by name if a search query is provided
        if (searchQuery != null && !searchQuery.isEmpty()) {
            projects = projects.stream()
                    .filter(projet -> projet.getNom().toLowerCase().contains(searchQuery.toLowerCase()))
                    .toList();
        }

        // Calculate total pages based on the filtered projects
        int totalProjects = projects.size();
        int totalPages = (int) Math.ceil((double) totalProjects / pageSize);

        // Set attributes for JSP
        request.setAttribute("projets", projects);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery); // To preserve the search query in the input field

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/projet.jsp");
        dispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int projectId = Integer.parseInt(request.getParameter("id"));
            Projet projet = new Projet();
            projet.setId(projectId); 

            projetService.delete(projet); 

            response.sendRedirect(request.getContextPath() + "/projet"); 
        } else {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            int equipeId = Integer.parseInt(request.getParameter("equipe"));
            String statut = request.getParameter("statut");
            LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
            LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));

            Projet projet = new Projet();
            projet.setNom(nom);
            projet.setDescription(description);
            
            Equipe equipe = new Equipe();
            equipe.setId(equipeId); 
            projet.setEquipe(equipe); 

            projet.setStatut(StatutProjet.valueOf(statut));
            projet.setDateDebut(dateDebut);
            projet.setDateFin(dateFin);

            projetService.saveProjet(projet);

            response.sendRedirect(request.getContextPath() + "/projet");
        }
    }

}
