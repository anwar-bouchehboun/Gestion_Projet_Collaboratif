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
            String memberIdParam = request.getParameter("memberId");
            System.out.println("Debug: Member ID parameter: " + memberIdParam);
    
            if (memberIdParam == null || memberIdParam.isEmpty()) {
                throw new ServletException("Member ID is required");
            }
    
            int memberId = Integer.parseInt(memberIdParam);
            System.out.println("Debug: Parsed Member ID: " + memberId);
    
            Members member = memberService.getMemberById(memberId);
            if (member == null) {
                throw new ServletException("Member not found");
            }
            System.out.println("Debug: Member found: " + member.getNom() + " " + member.getPrenom());
    
            List<Tache> tasks = tacheService.getTasksByMemberId(memberId);
            
            System.out.println("Debug: Number of tasks fetched: " + (tasks != null ? tasks.size() : "null"));
    
            request.setAttribute("member", member);
            request.setAttribute("tasks", tasks);
    
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member-tasks.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("Debug: Invalid member ID - " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid member ID");
        } catch (ServletException e) {
            System.out.println("Debug: ServletException - " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            System.out.println("Debug: Unexpected error - " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }
}