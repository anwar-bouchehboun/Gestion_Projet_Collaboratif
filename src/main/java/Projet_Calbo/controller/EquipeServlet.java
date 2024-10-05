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
import Projet_Calbo.utilis.InputValidateur;

public class EquipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EquipeService equipeService;

    public EquipeServlet() {
        super();
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
                listEquipes(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteTeam(request, response);
                break;
            default:
                listEquipes(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addTeam(request, response);
                break;
            case "updateTeam":
                updateTeam(request, response);
                break;
            case "delete":
                deleteTeam(request, response);
                break;
            default:
                listEquipes(request, response);
        }
    }

    private void listEquipes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int page = 1;
            int pageSize = 5;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            List<Equipe> equipes = equipeService.getAll();
            int totalItems = equipes.size();
            int totalPages = (int) Math.ceil((double) totalItems / pageSize);

            int startIndex = (page - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalItems);
            List<Equipe> paginatedEquipes = equipes.subList(startIndex, endIndex);

            request.setAttribute("equipes", paginatedEquipes);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("totalItems", totalItems);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage",
                    "Une erreur s'est produite lors de la récupération des équipes: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/equipe.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/equipe.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Equipe> equipes = equipeService.getAll();
        Equipe equipe = equipes.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (equipe != null) {
            request.setAttribute("equipe", equipe);
            System.out.println("Editing equipe: " + equipe.getId() + " - " + equipe.getNom());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/equipe.jsp");
            dispatcher.forward(request, response);
        } else {
            setErrorMessage(request, "Équipe non trouvée.");
            response.sendRedirect(request.getContextPath() + "/equipe?action=list");
        }
    }

    private void addTeam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String teamName = request.getParameter("teamName");
        
        if (!InputValidateur.validateTeamName(teamName)) {
            setErrorMessage(request, "Nom de l'équipe invalide. Il doit contenir au moins 3 caractères.");
            response.sendRedirect(request.getContextPath() + "/equipe?action=list");

            return;
        }
        Equipe newEquipe = new Equipe();
        newEquipe.setNom(teamName);
        equipeService.saveEquipe(newEquipe);
        setSuccessMessage(request, "Equipe ajoutée avec succès !");
        response.sendRedirect(request.getContextPath() + "/equipe?action=list");
    }

    private void updateTeam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String newName = request.getParameter("newName");
        if (!InputValidateur.validateTeamName(newName)) {
            setErrorMessage(request, "Données invalides. Veuillez vérifier les informations fournies.");
            response.sendRedirect(request.getContextPath() + "/equipe?action=list");
            return;
        }
        System.out.println("Updating equipe - ID: " + id + ", New Name: " + newName);

        List<Equipe> equipes = equipeService.getAll();
        Equipe equipeToUpdate = equipes.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (equipeToUpdate != null) {
            equipeToUpdate.setNom(newName);
            equipeService.updateEquipe(equipeToUpdate);
            setSuccessMessage(request, "Equipe modifiée avec succès !");
        } else {
            setErrorMessage(request, "Équipe non trouvée.");
        }
        response.sendRedirect(request.getContextPath() + "/equipe?action=list");
    }

    private void deleteTeam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        if (!InputValidateur.validateId(request.getParameter("id"))) {
            setErrorMessage(request, "ID d'équipe invalide.");
            response.sendRedirect(request.getContextPath() + "/equipe?action=list");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        List<Equipe> equipes = equipeService.getAll();
        Equipe equipeToDelete = equipes.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (equipeToDelete != null) {
            equipeService.deleteEquipe(equipeToDelete);
            setSuccessMessage(request, "Equipe supprimée avec succès !");
        } else {
            setErrorMessage(request, "Équipe non trouvée.");
        }
        response.sendRedirect(request.getContextPath() + "/equipe?action=list");
    }

    private void setSuccessMessage(HttpServletRequest request, String message) {
        request.getSession().setAttribute("successMessage", message);
    }

    private void setErrorMessage(HttpServletRequest request, String errorMessage) {
        request.getSession().setAttribute("errorMessage", errorMessage);
    }
}