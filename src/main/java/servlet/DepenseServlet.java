package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import entities.*;

public class DepenseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       
        HttpSession session = request.getSession(false);
        if (session == null || !"true".equals(session.getAttribute("auth"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            
            int previsionId = Integer.parseInt(request.getParameter("prevision_id"));
            double montant = Double.parseDouble(request.getParameter("montant"));
            Date dateDepense = Date.valueOf(request.getParameter("date_depense"));

            
            double totalDepenses = Depense.getTotalDepensesByPrevisionId(previsionId);
            double montantPrevision = Prevision.getMontantById(previsionId);

            if (totalDepenses + montant > montantPrevision) {
                response.sendRedirect("depenseForm?error=montant");
                return;
            }

           
            Depense depense = new Depense(previsionId, montant, dateDepense);
            depense.save();
            
            response.sendRedirect("depenseForm?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("depenseForm?error=true");
        }
    }
}
