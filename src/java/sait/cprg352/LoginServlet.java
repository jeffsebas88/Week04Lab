/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.cprg352;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.userservices;

/**
 *
 * @author 643507
 */

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   Cookie[] cookies = request.getCookies();
                   String cookieName = "userIdCookie";
                   String cookieValue = "";
                   for (Cookie cookie: cookies) {
                       if(cookieName.equals(cookie.getName()))
                           cookieValue=cookie.getValue();
                       
                   }
       
            if (!cookieValue.equals("")) {
                request.setAttribute("username", cookieValue);
                request.setAttribute("checked", "checked");
            }
            

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || username.isEmpty()
                || password == null || password.isEmpty()) {
            request.setAttribute("errormessage", "You must create a username and password.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);
            return;
        }
   
        userservices userservices = new userservices();
        userservices.setUsername(username);
        userservices.setPassword(password);

        if (userservices.getUsername().equals("") || userservices.getPassword().equals("")) {
            request.setAttribute("errormessage", "Username or password is incorrect.");
            request.setAttribute("username", userservices.getUsername());
            request.setAttribute("password", userservices.getPassword());
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);

        } else if (userservices.getUsername().equals("betty") || userservices.getUsername().equals("adam")
                && userservices.getPassword().equals("password")) {

            if (request.getParameter("checked") == null) {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userCookie")) {
                        cookie.setMaxAge(0);
                        //delete the cookie
                        cookie.setPath("/"); //allow the download application to access it
                        response.addCookie(cookie);
                    }
                }
            } else {
                Cookie c = new Cookie("userCookie", username);
                c.setMaxAge(60 * 900);
                //delete the cookie
                c.setPath("/"); //allow the download application to access it
                response.addCookie(c);
            }
           response.sendRedirect("home");
           
        }
         String logout = request.getParameter("logout");
        
        if (logout!= null) 
        {
            request.setAttribute("errormessage", "Logged out!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            
            
        }
    }
}
