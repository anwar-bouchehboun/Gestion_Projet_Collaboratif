package Projet_Calbo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Projet_Calbo.model.Tache;
import Projet_Calbo.services.TacheService;


public class TacheServlet extends HttpServlet {
    private TacheService tacheService;

    @Override
    public void init() throws ServletException {
        super.init();
        tacheService = new TacheService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listTaches(request, response);
                break;
            case "edit":
                editTache(request, response);
                break;
            default:
                listTaches(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addTache(request, response);
                break;
            case "update":
                updateTache(request, response);
                break;
            case "delete":
                deleteTache(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/tacheServlet?action=list");
        }
    }

    private void listTaches(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize = 10;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // Use default page 1
        }

        List<Tache> taches = tacheService.findAll(page, pageSize);
        int totalTaches = tacheService.countAll();
        int totalPages = (int) Math.ceil((double) totalTaches / pageSize);

        request.setAttribute("taches", taches);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/WEB-INF/view/tache/list.jsp").forward(request, response);
    }

    private void editTache(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Tache tache = tacheService.findById(id);
        request.setAttribute("tache", tache);
        request.getRequestDispatcher("/view/tache/form.jsp").forward(request, response);
    }


    private void addTache(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        Tache.Priorite priorite = Tache.Priorite.valueOf(request.getParameter("priorite"));
        Tache.Statut statut = Tache.Statut.valueOf(request.getParameter("statut"));
        LocalDate dateEcheance = LocalDate.parse(request.getParameter("dateEcheance"));

        Tache newTache = new Tache(null, titre, description, priorite, statut, LocalDate.now(), dateEcheance);
        tacheService.save(newTache);

        response.sendRedirect(request.getContextPath() + "/tacheServlet?action=list");
    }

    private void updateTache(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        Tache.Priorite priorite = Tache.Priorite.valueOf(request.getParameter("priorite"));
        Tache.Statut statut = Tache.Statut.valueOf(request.getParameter("statut"));
        LocalDate dateEcheance = LocalDate.parse(request.getParameter("dateEcheance"));
    
        Tache updatedTache = new Tache(id, titre, description, priorite, statut, LocalDate.now(), dateEcheance);
        tacheService.update(updatedTache);
    
        response.sendRedirect(request.getContextPath() + "/tacheServlet?action=list");
    }
    
    private void deleteTache(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        tacheService.delete(id);
        response.sendRedirect(request.getContextPath() + "/tacheServlet?action=list");
    }
}
