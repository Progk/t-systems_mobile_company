package org.tsystems.mobile_company.servlets;

import com.google.gson.Gson;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.Contract;
import org.tsystems.mobile_company.entities.LockType;
import org.tsystems.mobile_company.entities.Option;
import org.tsystems.mobile_company.entities.Plan;
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
import java.util.function.BooleanSupplier;

/**
 * Created by sergey on 08.07.15.
 */
@WebServlet(name = "UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && httpSession.getAttributeNames().hasMoreElements()) {
            String type = (String) request.getParameter("type");
            switch (type) {
                case "selectContract":
                    selectContract(request, response);
                    break;
                case "clickLockButton":
                    clickLockButton(request, response);
                    break;
                case "selectNewPlan":
                    selectNewPlan(request, response);
                    break;
                case "selectOptionClient":
                   selectOptionsClient(request, response);
                    break;
            }
            //request.getRequestDispatcher("/user.jsp").forward(request, response);
        } else if (httpSession == null){
            request.setAttribute("errorMessage", "Session Expired");
        } else {
            pw.write("no login");
        }
    }



    private void clickLockButton(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String contractNumber = (String) httpSession.getAttribute("contractNumber");
        Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
        try {
            Contract contract = ContractServices.getInstance().getContractByNumber(contractNumber);
            ContractServices.getInstance().changeLockType(contract, isAdmin);
            ContractServices.getInstance().updateContract(contract);
            httpSession.setAttribute("contractLockTypeId", contract.getLockType().getId());
        } catch (ECareException e) {
            e.printStackTrace();
        }
    }

    private void selectContract(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession httpSession = request.getSession(false);
        String contractNumber = (String) request.getParameter("number");
        List<Plan> availablePlanList = (ArrayList<Plan>) httpSession.getAttribute("availablePlanList");
        List<Option> availableOptionList = (ArrayList<Option>) httpSession.getAttribute("availableOptionList");
        availableOptionList.clear();
        try {
            Contract contract = ContractServices.getInstance().getContractByNumber(contractNumber);
            Plan plan = PlanServices.getInstance().findPlanById(contract.getPlanId());
            availablePlanList = PlanServices.getInstance().getAllPlan();
            availablePlanList.remove(plan);
            Map<Option, Boolean> optionsForPlan = new HashMap<Option, Boolean>();
            Map<String, String> selectedOptions = new LinkedHashMap<String, String>();
            String[] selectedOptionArr = new String[contract.getSelectedOptions().size()];
            for (Option p: plan.getOptions())
                optionsForPlan.put(p, new Boolean(false));
            for (int i = 0; i < contract.getSelectedOptions().size(); i++){
                selectedOptions.put(String.valueOf(i+1),contract.getSelectedOptions().get(i).getName());
                selectedOptionArr[i] = contract.getSelectedOptions().get(i).getName();
            }

            UserServices.getInstance().findAvailableOptions(optionsForPlan, selectedOptionArr);

            httpSession.setAttribute("optionsForPlanMap", optionsForPlan);
            httpSession.setAttribute("availablePlanList", availablePlanList);;
            httpSession.setAttribute("contractNumber", contract.getNumber());
            httpSession.setAttribute("contractPlan", plan.getName());
            httpSession.setAttribute("contractLockTypeId", contract.getLockType().getId());
            String json= new Gson().toJson(selectedOptions);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (ECareException e) {
            e.printStackTrace();
        }
    }

    private void selectNewPlan(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String newPlan = (String) request.getParameter("plan");
        String contractNumber = (String) httpSession.getAttribute("contractNumber");

        try { //delete all options in this contract(from old plan)
            Contract contract = ContractServices.getInstance().getContractByNumber(contractNumber);

            for (Option o: contract.getSelectedOptions()) {
                o.getContracts().remove(contract);
            }
            contract.getSelectedOptions().clear();
            ContractServices.getInstance().updateContract(contract);
            Plan plan = PlanServices.getInstance().findPlanByName(newPlan);
            contract.setPlanId(plan.getId());
            contract.setSelectedOptions(plan.getOptions());
            ContractServices.getInstance().updateContract(contract);
            Map<Option, Boolean> optionsForPlan = (HashMap<Option, Boolean>)httpSession.getAttribute("optionsForPlanMap");
            optionsForPlan.clear();
            for (Option p: plan.getOptions())
                optionsForPlan.put(p, new Boolean(false));
            httpSession.setAttribute("optionsForPlanMap", optionsForPlan);
        } catch (ECareException e) {
            e.printStackTrace();
        }
    }

    private void selectOptionsClient(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String arr[] = request.getParameterValues("selectedOptionArr[]");
        Map<Option, Boolean> options = (Map<Option, Boolean>)httpSession.getAttribute("optionsForPlanMap");
        UserServices.getInstance().findAvailableOptions(options, arr);
        httpSession.setAttribute("optionsForPlanMap", options);
    }
}
