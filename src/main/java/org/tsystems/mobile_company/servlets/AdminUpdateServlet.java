package org.tsystems.mobile_company.servlets;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
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
    UserServices userServices = UserServices.getInstance();
    OptionServices optionServices = OptionServices.getInstance();
    PlanServices planServices = PlanServices.getInstance();
    ContractServices contractServices = ContractServices.getInstance();

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
                    addNewUser(request, response);
                    break;
                case "selectPlanNewClient":
                    selectPlanNewClient(request, response);
                    break;
                case "selectedOptionArrNewClient":
                    selectOptionNewClient(request, response);
                    break;
                case "editPlan":
                    editPlan(request, response);
                    break;
                case "allUserShowContract":
                    allUSerShowContract(request, response);
                    break;
                case "editAdminUser" :
                    editAdminUser(request, response);
                    break;
                case "searchAdminUser" :
                    searchAdminUser(request, response);
                    break;
            }
            //request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } else if (httpSession == null){
            request.setAttribute("errorMessage", "Session Expired");
        } else {
            pw.write("no login");
        }
    }

    private void searchAdminUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String inputNumber = (String)request.getParameter("inputNumber");
        String planName = planServices
        List<Plan> planList = null;
        try {

            planList = planServices.getAllPlan();
        } catch (ECareException e) {
            e.printStackTrace();
        }
        User userObject = null;
        Contract contract = null;
        try {
            contract = contractServices.getContractByNumber(inputNumber);
            userObject = contractServices.getContractByNumber(inputNumber).getUser();
        } catch (ECareException e) {
            e.printStackTrace();
        }
        userObject.getContracts().get(0).
        httpSession.setAttribute("allPlanList", planList);
        httpSession.setAttribute("adminEditUser", userObject);
        httpSession.setAttribute("adminEditUserNumber", contract.get);
    }

    private void editAdminUser(HttpServletRequest request, HttpServletResponse response) {

    }

    private void allUSerShowContract(HttpServletRequest request, HttpServletResponse response) {
        String clickUser = (String) request.getParameter("click");
        String userEmail = clickUser.split("_")[1];
        User userObject = userServices.findUserByEmail(userEmail);
        request.getSession().setAttribute("allUserShowContract", userObject);
    }

    private void editPlan(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String editedPlan = (String)request.getParameter("editedPlan");
        String newName = (String)request.getParameter("newName");
        String newCost = (String)request.getParameter("newCost");
        String newOptions = (String)request.getParameter("newOptions");
        String [] newOptionsArr = newOptions.split(",");
        try {
            Plan plan = planServices.findPlanByName(newName);
            plan.setPrice(Integer.valueOf(newCost));
            for (String s : newOptionsArr) {
                Option option = optionServices.findOptionByName(s);
                plan.getOptions().add(option);
            }
        } catch (ECareException e) {
            planServices.createNewPlan(newName, Integer.valueOf(newCost), newOptionsArr);
        }

    }

    private void selectOptionNewClient(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String arr[] = request.getParameterValues("selectedOptionArr[]");
        Map<Option, Boolean> options = (Map<Option, Boolean>)httpSession.getAttribute("optionsForPlanMap");
        UserServices.getInstance().findAvailableOptions(options, arr);
        httpSession.setAttribute("optionsForPlanMap", options);
    }

    private void selectPlanNewClient(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
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
    }

    private void addNewUser(HttpServletRequest request, HttpServletResponse response) {
        String name = (String)request.getParameter("name");
        String surname = (String)request.getParameter("surname");
        String date = (String)request.getParameter("date");
        String passport = (String)request.getParameter("passport");
        String address = (String)request.getParameter("address");
        String email = (String)request.getParameter("email");
        String password = (String)request.getParameter("password");
        String number= (String)request.getParameter("number");
        String selectedPlan = (String)request.getParameter("plan");
        String option= (String) request.getParameter("options");
        String options[] = null;
        if (option != null) {
            options = option.split(",");
        }
        Plan plan = null;
        try {
            plan = planServices.findPlanByName(selectedPlan);
        } catch (ECareException e) {
            e.printStackTrace();
        }
        User user = userServices.addUser(name, surname, date, passport, address, email, password, false);
        Contract newContract = contractServices.createNewContract(user, number, plan.getId(), Contract.LOCKED_NONE);
        contractServices.addOptions(newContract, Arrays.asList(options));
        //planServices.createNewPlan(user, selectedPlan);
    }
}
