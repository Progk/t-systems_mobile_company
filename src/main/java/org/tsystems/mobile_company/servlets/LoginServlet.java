package org.tsystems.mobile_company.servlets;

import org.tsystems.mobile_company.entities.User;
import org.tsystems.mobile_company.entities.UserType;
import org.tsystems.mobile_company.services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sergey on 01.07.15.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        User user = UserServices.getInstance().findUserByEmailAndPassword(userEmail, userPassword);
        ServletContext context = getServletContext();
        if (user.getUserTypeId() == UserType.ADMIN_TYPE)
            response.sendRedirect(context.getContextPath()+"/AdminServlet");
        else if (user.getUserTypeId() == UserType.USER_TYPE)
            response.sendRedirect(context.getContextPath()+"/UserServlet");
        else
            request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
