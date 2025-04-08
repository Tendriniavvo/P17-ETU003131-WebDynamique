package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import entities.Prevision;

public class DepenseFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       
        HttpSession session = request.getSession(false);
        if (session == null || !"true".equals(session.getAttribute("auth"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
          
            List<Prevision> previsions = Prevision.findAll();
            request.setAttribute("previsions", previsions);
            
           
            request.getRequestDispatcher("DepenseForm.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("DepenseForm.jsp?error=true");
        }
    }
}
