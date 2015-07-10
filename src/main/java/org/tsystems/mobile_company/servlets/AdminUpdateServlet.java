package org.tsystems.mobile_company.servlets;

import org.tsystems.mobile_company.dao.OptionDAO;
import org.tsystems.mobile_company.entities.Contract;
import org.tsystems.mobile_company.entities.Option;
import org.tsystems.mobile_company.entities.Plan;
import org.tsystems.mobile_company.entities.User;
import org.tsystems.mobile_company.services.ContractServices;
import org.tsystems.mobile_company.services.OptionServices;
import org.tsystems.mobile_company.services.PlanServices;
import org.tsystems.mobile_company.services.UserServices;
import org.tsystems.mobile_company.utils.ECareException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by sergey on 10.07.15.
 */
@WebServlet(name = "AdminUpdateServlet")
public class AdminUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && httpSession.getAttributeNames().hasMoreElements()) {
            String type = (String) request.getParameter("type");
            String contractNumber = null;
            switch (type) {
                case "addNewUser":
                    String name = (String)request.getParameter("name");
                    String surname = (String)request.getParameter("surname");
                    String date = (String)request.getParameter("date");
                    String passport = (String)request.getParameter("passport");
                    String address = (String)request.getParameter("address");
                    String email = (String)request.getParameter("email");
                    String password = (String)request.getParameter("password");
                    String number= (String)request.getParameter("number");
                    String planContract = (String)request.getParameter("plan");
                    String option[] = request.getParameterValues("selectedOptionArr[]");
                    User user = UserServices.getInstance().addUser(name, surname, date, passport, address, email, password, number, false);
                    break;
                case "selectPlanNewClient":
                    String planName = (String)request.getParameter("plan");

                    try {
                        Map<Option, Boolean> options = new HashMap<>();
                        Plan plan = PlanServices.getInstance().findPlanByName(planName);
                        for (Option p: plan.getOptions())
                            options.put(p, new Boolean(false));
                        request.getSession().getAttribute("name");
                        httpSession.setAttribute("optionsForPlanMap", options);
                    } catch (ECareException e) {
                        e.printStackTrace();
                    }
                    break;
                case "selectedOptionArrNewClient":
                    String arr[] = request.getParameterValues("selectedOptionArr[]");
                    Map<Option, Boolean> options = (Map<Option, Boolean>)httpSession.getAttribute("optionsForPlanMap");
                    UserServices.getInstance().findAvailableOptions(options, arr);
                    httpSession.setAttribute("optionsForPlanMap", options);
                    break;
            }

            //request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } else if (httpSession == null){
            request.setAttribute("errorMessage", "Session Expired");
        } else {
            pw.write("no login");
        }
    }
}
