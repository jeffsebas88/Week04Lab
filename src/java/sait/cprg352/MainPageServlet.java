/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.cprg352;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 643507
 */
public class MainPageServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String url = request.getRequestURI();
      
        HttpSession sess = request.getSession();
        String userName = (String) sess.getAttribute("username");
       
        if (userName == null) {
            response.sendRedirect("/logout");
            return;
        }
        
request.setAttribute("username", userName);
          getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
            
          
        
}
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }  
 
}
