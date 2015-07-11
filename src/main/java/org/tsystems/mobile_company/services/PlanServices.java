package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.dao.PlanDAO;
import org.tsystems.mobile_company.dao.UserDAO;
import org.tsystems.mobile_company.entities.Plan;
import org.tsystems.mobile_company.utils.ECareException;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

/**
 * Created by sergey on 07.07.15.
 */
public class PlanServices {
    private static PlanServices planServices;
    private PlanDAO planDAO = PlanDAO.getInstance();

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
}
