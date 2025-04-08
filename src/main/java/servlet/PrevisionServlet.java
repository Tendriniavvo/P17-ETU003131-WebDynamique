package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import entities.Prevision;

public class PrevisionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession(false);
        if (session == null || !"true".equals(session.getAttribute("auth"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
           
            String description = request.getParameter("description");
            double montant = Double.parseDouble(request.getParameter("montant"));
            Date datePrevision = Date.valueOf(request.getParameter("date_prevision"));

            
            Prevision prevision = new Prevision(description, montant, datePrevision);
            prevision.save();
            
            response.sendRedirect("PrevisionForm.jsp?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("PrevisionForm.jsp?error=true");
        }
    }
}
