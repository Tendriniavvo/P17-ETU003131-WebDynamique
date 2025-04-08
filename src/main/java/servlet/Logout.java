package servlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
           
            java.util.Enumeration<String> attributes = session.getAttributeNames();
            while(attributes.hasMoreElements()) {
                String attribute = attributes.nextElement();
                session.removeAttribute(attribute);
            }
            
            session.invalidate();
            
            
            Cookie[] cookies = req.getCookies();
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals("JSESSIONID")) {
                        cookie.setMaxAge(0);
                        cookie.setValue(null);
                        cookie.setPath("/");
                        res.addCookie(cookie);
                        break;
                    }
                }
            }
        }
        
 
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setHeader("Expires", "0");
        
        res.sendRedirect("login.jsp");
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
