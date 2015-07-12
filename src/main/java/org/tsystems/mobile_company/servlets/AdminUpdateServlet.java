package org.tsystems.mobile_company.servlets;

import com.google.gson.Gson;
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
                case "selectPlanEditUser":
                    selectPlanEditClient(request, response);
                    break;
                case "selectedOptionArrNewClient":
                    selectOptionNewClient(request, response);
                    break;
                case "selectedOptionArrEditClient":
                    selectOptionEditClient(request, response);
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
                case "blockUser" :
                    blockUser(request, response);
                    break;
                case "unblockUser" :
                    unblockUser(request, response);
                    break;
                case "editAdminPlan":
                    editAdminPlan(request, response);
                    break;
            }
        } else if (httpSession == null){
            request.setAttribute("errorMessage", "Session Expired");
        } else {
            pw.write("no login");
        }
    }

    private void editAdminPlan(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String plan = request.getParameter("plan");
        String planNewName = request.getParameter("planNewName");
        String planNewPrice = request.getParameter("planNewPrice");
        String[] options = request.getParameterValues("editPlan" + plan);
        planServices.updatePlan(plan, planNewName, planNewPrice, options);
        List<Plan> planList = null;
        try {
            planList = planServices.getAllPlan();
        } catch (ECareException e) {
            e.printStackTrace();
        }
        httpSession.setAttribute("planList", planList);
    }

    private void unblockUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String user = request.getParameter("email");
        User u = userServices.findUserByEmail(user);
        userServices.unblockUser(user);
        Map<User, Boolean> allUsers = (Map<User, Boolean>) httpSession.getAttribute("allSimpleUsersMap");
        allUsers.put(u, false);
        httpSession.setAttribute("allSimpleUsersMap", allUsers);
    }

    private void blockUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String user = request.getParameter("email");
        userServices.blockUser(user);
        User u = userServices.findUserByEmail(user);
        Map<User, Boolean> allUsers = (Map<User, Boolean>) httpSession.getAttribute("allSimpleUsersMap");
        allUsers.put(u, true);
        httpSession.setAttribute("allSimpleUsersMap", allUsers);
    }

    private void selectOptionEditClient(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String arr[] = request.getParameterValues("selectedOptionArr[]");
       /* for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].split("_")[0];
        }*/
        Map<Option, Boolean> options = (Map<Option, Boolean>)httpSession.getAttribute("optionsEditUser");
        UserServices.getInstance().findAvailableOptions(options, arr);
        httpSession.setAttribute("optionsEditUser", options);
    }

    private void searchAdminUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession httpSession = request.getSession(false);
        String inputNumber = (String)request.getParameter("inputNumber");
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

        Map<Option, Boolean> options = new HashMap<>();
        Plan plan = contract.getPlanId();
        for (Option o : plan.getOptions()) {
            options.put(o, false);
        }
        String[] selectedOptionArr = new String[contract.getSelectedOptions().size()];
        for (int i = 0; i < contract.getSelectedOptions().size(); i++){
            selectedOptionArr[i] = contract.getSelectedOptions().get(i).getName();
        }
        userServices.findAvailableOptions(options, selectedOptionArr);

        Map<String, String> selectedOptions = new LinkedHashMap<String, String>();
        for (Option o: contract.getSelectedOptions()){
            selectedOptions.put(String.valueOf(o.getId()), o.getName());
        }

        httpSession.setAttribute("optionsEditUser", options);
        httpSession.setAttribute("allPlanList", planList);
        httpSession.setAttribute("adminEditUser", userObject);
        httpSession.setAttribute("adminEditUserNumber", contract.getNumber());

        String json= new Gson().toJson(selectedOptions);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void editAdminUser(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String date = request.getParameter("date");
        String passport = request.getParameter("passport");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String number= request.getParameter("number");
        String selectedPlan = request.getParameter("plan");
        String option=  request.getParameter("options");
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

    private void selectPlanEditClient(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String arr[] = request.getParameterValues("selectedOptionArr[]");
        Map<Option, Boolean> options = (Map<Option, Boolean>)httpSession.getAttribute("optionsEditUser");
        UserServices.getInstance().findAvailableOptions(options, arr);
        httpSession.setAttribute("optionsEditUser", options);
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
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String date = request.getParameter("date");
        String passport = request.getParameter("passport");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String number= request.getParameter("number");
        String selectedPlan = request.getParameter("plan");
        String option= request.getParameter("options");
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
        Contract newContract = contractServices.createNewContract(user, number, plan, Contract.LOCKED_NONE);
        contractServices.addOptions(newContract, Arrays.asList(options));
    }
}
