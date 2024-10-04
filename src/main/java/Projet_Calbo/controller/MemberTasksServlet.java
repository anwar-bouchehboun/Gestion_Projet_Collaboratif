package Projet_Calbo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Projet_Calbo.model.Members;
import Projet_Calbo.model.Tache;
import Projet_Calbo.services.MemberService;
import Projet_Calbo.services.TacheService;

public class MemberTasksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService memberService;
    private TacheService tacheService;

    @Override
    public void init() throws ServletException {
        super.init();
        memberService = new MemberService();
        tacheService = new TacheService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int memberId = Integer.parseInt(request.getParameter("memberId"));

            Members member = new Members();
            member.setId(memberId);
            List<Tache> tasks = tacheService.getTasksByMemberId(memberId);

            request.setAttribute("member", member);
            request.setAttribute("tasks", tasks);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member-tasks.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid member ID");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }
}