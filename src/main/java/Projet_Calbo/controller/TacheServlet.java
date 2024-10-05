package Projet_Calbo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Projet_Calbo.model.Members;
import Projet_Calbo.model.PrioriteEnum;
import Projet_Calbo.model.Projet;
import Projet_Calbo.model.Statut;
import Projet_Calbo.model.Tache;
import Projet_Calbo.services.MemberService;
import Projet_Calbo.services.ProjetService;
import Projet_Calbo.services.TacheService;

public class TacheServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TacheService tacheService;
    private ProjetService projetService;
    private MemberService memberService;

    public TacheServlet() {
        super();
        tacheService = new TacheService();
        projetService = new ProjetService();
        memberService = new MemberService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listTaches(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteTache(request, response);
                break;
            default:
                listTaches(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addTache(request, response);
                break;
            case "edit":
                updateTache(request, response);
                break;
            default:
                listTaches(request, response);
        }
    }

    private void listTaches(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int page = 1;
            int pageSize = 5;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            List<Tache> taches = tacheService.getTachePage(page, pageSize);
            int totalPages = tacheService.getTotalPages(pageSize);

            List<Projet> projets = projetService.getAllProjets();
            List<Members> members = memberService.getMemberPage(1, Integer.MAX_VALUE);

            request.setAttribute("taches", taches);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("projets", projets);
            request.setAttribute("members", members);

            request.setAttribute("priorites", PrioriteEnum.values());
            request.setAttribute("statuts", Statut.values());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage",
                    "Une erreur s'est produite lors de la récupération des tâches: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/tache.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Projet> projets = projetService.getAllProjets();
        List<Members> members = memberService.getMemberPage(1, Integer.MAX_VALUE);
        request.setAttribute("projets", projets);
        request.setAttribute("members", members);
        request.setAttribute("priorites", PrioriteEnum.values());
        request.setAttribute("statuts", Statut.values());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/tache-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tache tache = tacheService.getTacheById(id);
        List<Projet> projets = projetService.getAllProjets();
        List<Members> members = memberService.getMemberPage(1, Integer.MAX_VALUE);
        request.setAttribute("tache", tache);
        request.setAttribute("projets", projets);
        request.setAttribute("members", members);
        request.setAttribute("priorites", PrioriteEnum.values());
        request.setAttribute("statuts", Statut.values());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/tache-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addTache(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        PrioriteEnum priorite = PrioriteEnum.valueOf(request.getParameter("priorite"));
        Statut statut = Statut.valueOf(request.getParameter("statut"));
        LocalDate dateCreation = LocalDate.parse(request.getParameter("dateCreation"));
        LocalDate dateEcheance = LocalDate.parse(request.getParameter("dateEcheance"));
        int projetId = Integer.parseInt(request.getParameter("projetId"));
        int membreId = Integer.parseInt(request.getParameter("membreId"));

        Projet projet = new Projet();
        projet.setId(projetId);

        Members membre = new Members();
        membre.setId(membreId);

        Tache newTache = new Tache(titre, description, priorite, statut, dateCreation, dateEcheance, projet, membre);
        boolean success = tacheService.addTache(newTache);

        if (success) {
            request.getSession().setAttribute("successMessage", "Tâche ajoutée avec succès !");
        } else {
            request.getSession().setAttribute("errorMessage", "Erreur lors de l'ajout de la tâche.");
        }

        response.sendRedirect(request.getContextPath() + "/tache?action=list");
    }

    private void updateTache(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        PrioriteEnum priorite = PrioriteEnum.valueOf(request.getParameter("priorite"));
        Statut statut = Statut.valueOf(request.getParameter("statut"));
        LocalDate dateCreation = LocalDate.parse(request.getParameter("dateCreation"));
        LocalDate dateEcheance = LocalDate.parse(request.getParameter("dateEcheance"));
        int projetId = Integer.parseInt(request.getParameter("projetId"));
        int membreId = Integer.parseInt(request.getParameter("membreId"));

        Projet projet = new Projet();
        projet.setId(projetId);

        Members membre = new Members();
        membre.setId(membreId);

        Tache tache = new Tache(titre, description, priorite, statut, dateCreation, dateEcheance, projet, membre);
        tache.setId(id);
        tacheService.updateTache(tache);

        request.getSession().setAttribute("successMessage", "Tâche mise à jour avec succès !");
        response.sendRedirect(request.getContextPath() + "/tache?action=list");
    }

    private void deleteTache(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tache tache = tacheService.getTacheById(id);
        if (tache != null) {
            tacheService.deleteTache(tache);
            request.getSession().setAttribute("successMessage", "Tâche supprimée avec succès !");
        } else {
            request.getSession().setAttribute("errorMessage", "Tâche non trouvée.");
        }
        response.sendRedirect(request.getContextPath() + "/tache?action=list");
    }


}