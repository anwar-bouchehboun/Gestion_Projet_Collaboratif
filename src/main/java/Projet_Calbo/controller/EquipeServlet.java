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

public class EquipeServlet extends HttpServlet {
    private EquipeService equipeService;

    public EquipeServlet() {
        super();
        equipeService = new EquipeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "list" : action;

        switch (action) {
            case "list":
                listEquipes(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "showEditForm":
                showEditForm(request, response);
                break;
            default:
                listEquipes(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "addTeam":
                    addTeam(request, response);
                    break;
                case "updateTeam":
                    updateTeam(request, response);
                    break;
                case "deleteTeam":
                    deleteTeam(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/equipe?action=list");
            }
        } catch (Exception e) {
            handleException(e, request, response);
        }
    }

    private void listEquipes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = getPageNumber(request);
        int pageSize = 5;

        List<Equipe> equipes = equipeService.getAll();
        int totalEquipes = equipes.size();
        int totalPages = (int) Math.ceil((double) totalEquipes / pageSize);

        setRequestAttributes(request, equipes, page, totalPages);
        forwardToJsp(request, response, "/views/equipe.jsp");
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToJsp(request, response, "/views/equipe_add_form.jsp");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Equipe equipe = equipeService.getEquipeById(id);

        if (equipe != null) {
            request.setAttribute("equipe", equipe);
            forwardToJsp(request, response, "/views/equipe_edit_form.jsp");
        } else {
            setErrorMessage(request, "Équipe non trouvée.");
            response.sendRedirect(request.getContextPath() + "/equipe?action=list");
        }
    }

    private void addTeam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String teamName = request.getParameter("teamName");
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
        Equipe equipeToUpdate = equipeService.getEquipeById(id);

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
        int id = Integer.parseInt(request.getParameter("id"));
        Equipe equipeToDelete = equipeService.getEquipeById(id);

        if (equipeToDelete != null) {
            equipeService.deleteEquipe(equipeToDelete);
            setSuccessMessage(request, "Equipe supprimée avec succès !");
        } else {
            setErrorMessage(request, "Équipe non trouvée.");
        }
        response.sendRedirect(request.getContextPath() + "/equipe?action=list");
    }

    private int getPageNumber(HttpServletRequest request) {
        String pageParam = request.getParameter("page");
        return (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
    }

    private void setRequestAttributes(HttpServletRequest request, List<Equipe> equipes, int page, int totalPages) {
        request.setAttribute("equipes", equipes);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
    }

    private void setSuccessMessage(HttpServletRequest request, String message) {
        request.getSession().setAttribute("message", message);
    }

    private void setErrorMessage(HttpServletRequest request, String errorMessage) {
        request.getSession().setAttribute("errorMessage", errorMessage);
    }

    private void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String jspPath)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
        dispatcher.forward(request, response);
    }

    private void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        e.printStackTrace();
        setErrorMessage(request, "Une erreur s'est produite lors du traitement de la demande.");
        response.sendRedirect(request.getContextPath() + "/equipe?action=list");
    }
}