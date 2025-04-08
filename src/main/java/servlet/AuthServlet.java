package servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;


public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        HttpSession existingSession = req.getSession(false);
        if (existingSession != null && "true".equals(existingSession.getAttribute("auth"))) {
            res.sendRedirect("PrevisionForm.jsp");
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(username) && "admin".equals(password)) {
            HttpSession session = req.getSession(true); 
            session.setAttribute("auth", "true");
            session.setAttribute("username", username);
            
            session.setMaxInactiveInterval(30 * 60);
            res.sendRedirect("PrevisionForm.jsp"); 
        } else {
            req.setAttribute("errorMessage", "Nom d'utilisateur ou mot de passe incorrect");
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, res);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        if (session != null && "true".equals(session.getAttribute("auth"))) {
            res.sendRedirect("PrevisionForm.jsp");
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}
