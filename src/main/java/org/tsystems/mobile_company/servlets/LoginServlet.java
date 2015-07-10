package org.tsystems.mobile_company.servlets;

import org.tsystems.mobile_company.dao.PlanDAO;
import org.tsystems.mobile_company.entities.*;
import org.tsystems.mobile_company.services.PlanServices;
import org.tsystems.mobile_company.services.UserServices;
import org.tsystems.mobile_company.utils.ECareException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        try {
            User user = UserServices.getInstance().findUserByEmailAndPassword(userEmail, userPassword);
            HttpSession httpSession = request.getSession();
            httpSession.setMaxInactiveInterval(60*60); //60 sec for now
            httpSession.setAttribute("email", userEmail);
            httpSession.setAttribute("user", user);
            ServletContext context = getServletContext();
            if (user.getUserTypeId() == UserType.ADMIN_TYPE) {
                Boolean isAdmin = false;
                List<Plan> allPlanList = PlanServices.getInstance().getAllPlan();
                httpSession.setAttribute("isAdmin", isAdmin);
                httpSession.setAttribute("allPlanList", allPlanList);
                httpSession.setAttribute("errorMessage", "Select Contract");
                response.sendRedirect(context.getContextPath() + "/AdminServlet");
            } else {
                List<Option> availableOptionList = new ArrayList<>();
                List<Plan> availablePlanList = new ArrayList<>();
                String contractPlan = new String();
                String contractNumber = new String();
                Integer contractLockTypeId = 0;
                Boolean isAdmin = false;
                httpSession.setAttribute("isAdmin", isAdmin);
                httpSession.setAttribute("availablePlanList", availablePlanList);
                httpSession.setAttribute("availableOptionList", availableOptionList);
                httpSession.setAttribute("contractNumber", contractNumber);
                httpSession.setAttribute("contractPlan", contractPlan);
                httpSession.setAttribute("contractLockTypeId", contractLockTypeId);
                httpSession.setAttribute("errorMessage", "Select Contract");

                response.sendRedirect(context.getContextPath() + "/UserServlet");
            }
        } catch (ECareException e) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
