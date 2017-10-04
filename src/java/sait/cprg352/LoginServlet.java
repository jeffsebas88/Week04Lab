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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import services.userservices;

/**
 *
 * @author 643507
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession sess = request.getSession();
      
        Cookie[] cookies = request.getCookies();
        String cookieName = "userIdCookie";
        String cookieValue = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                }

            }
        }
        if (!cookieValue.equals("")) {
            request.setAttribute("username", cookieValue);
            request.setAttribute("checked", "checked");
        }
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            sess.removeAttribute("username");
            // request.removeAttribute("action");
            request.setAttribute("errorMessage", "Sucessfuly logout");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        String sessUser = (String) sess.getAttribute("username");
        if (sessUser != null) {
            response.sendRedirect("/home");
            return;
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
             HttpSession sess = request.getSession();
        sess.setAttribute("username", username);
    response.sendRedirect("/home");
            

        }
       
                
        }
     
    }
