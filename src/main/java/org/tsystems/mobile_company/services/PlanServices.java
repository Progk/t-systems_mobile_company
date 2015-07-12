package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.dao.OptionDAO;
import org.tsystems.mobile_company.dao.PlanDAO;
import org.tsystems.mobile_company.dao.UserDAO;
import org.tsystems.mobile_company.entities.Option;
import org.tsystems.mobile_company.entities.Plan;
import org.tsystems.mobile_company.entities.User;
import org.tsystems.mobile_company.utils.ECareException;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by sergey on 07.07.15.
 */
public class PlanServices {
    private static PlanServices planServices;
    private PlanDAO planDAO = PlanDAO.getInstance();
    private OptionDAO optionDAO = OptionDAO.getInstance();

    private PlanServices() {

    }

    public static synchronized PlanServices getInstance() {
        if (planServices == null)
            planServices = new PlanServices();
        return planServices;
    }

    public Plan findPlanById(int id) throws ECareException {
        Plan plan = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            plan = planDAO.find(id);
            EntityManagerFactoryInstance.commitTransaction();
        } catch (IllegalArgumentException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("No plan with this id");
        }
        return plan;
    }

    public Plan createNewPlan(String name, int price, String[] options) {
        List<Option> optionsList = new LinkedList<>();
        EntityManagerFactoryInstance.beginTransaction();
        Plan plan = new Plan(name, price, optionsList);
        for (String s : options) {
            Option option = optionDAO.findByName(s);
            plan.getOptions().add(option);
        }
        planDAO.addOrUpdate(plan);
        EntityManagerFactoryInstance.commitTransaction();
        return plan;
    }


    public Plan findPlanByName(String name) throws ECareException {
        Plan plan = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            plan = planDAO.findPlanByName(name);
            EntityManagerFactoryInstance.commitTransaction();
        } catch (NoResultException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("No plan with this name");
        }
        return plan;
    }

    public List<Plan> getAllPlan() throws ECareException {
        List<Plan> plan = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            plan = planDAO.getAll();
            EntityManagerFactoryInstance.commitTransaction();
        } catch (IllegalArgumentException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("Error while read plans");
        }
        return plan;
    }

    public void createNewPlan(User user, String newPlan) {
        try {
            Plan plan = findPlanByName(newPlan);
        } catch (ECareException e) {
            e.printStackTrace();
        }
    }

    public void updatePlan(String oldName, String planNewName, String planNewPrice, String[] options) {

        try {
            Plan plan2 = findPlanByName(oldName);

            Plan plan = planDAO.find(plan2.getId());
            List<Option> optionAllList = optionDAO.getAll();
            List<Option> optionNewList = new LinkedList<>();
            if (options != null) {
            for (String o: options) {
                optionNewList.add(optionDAO.findByName(o));
            }
                EntityManagerFactoryInstance.beginTransaction();
                for (Option o : optionAllList) {
                    if (plan.getOptions().contains(o) && optionNewList.contains(o)) {
                        continue;
                    } else if (!plan.getOptions().contains(o) && optionNewList.contains(o)) {
                        plan.getOptions().add(o);
                    } else if (plan.getOptions().contains(o) && !optionNewList.contains(o)) {
                        plan.getOptions().remove(o);
                    }
                    plan.setName(planNewName);
                    plan.setPrice(Integer.valueOf(planNewPrice));
                    planDAO.addOrUpdate(plan);
                }
                EntityManagerFactoryInstance.commitTransaction();
            } else {
                EntityManagerFactoryInstance.beginTransaction();
                plan.getOptions().clear();
                planDAO.addOrUpdate(plan);
                EntityManagerFactoryInstance.commitTransaction();
            }

        } catch (ECareException e) {
            e.printStackTrace();
        }
    }
}
