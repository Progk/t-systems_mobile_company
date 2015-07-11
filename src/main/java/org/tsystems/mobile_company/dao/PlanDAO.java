package org.tsystems.mobile_company.dao;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.Plan;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sergey on 07.07.15.
 */
public class PlanDAO implements IEntityDAO<Plan> {

    private EntityManager entityManager = EntityManagerFactoryInstance.getEntityManager();
    private static PlanDAO planDAOInstance;

    public static PlanDAO getInstance() {
        if (planDAOInstance == null) {
            synchronized (ContractDAO.class) {
                if (planDAOInstance == null)
                    planDAOInstance = new PlanDAO();
            }
        }
        return planDAOInstance;
    }

    private PlanDAO() {

    }


    public Plan addOrUpdate(Plan entity) {
        return entityManager.merge(entity);
    }

    public Plan find(int id) {
        return entityManager.find(Plan.class, id);
    }

    public void remove(Plan entity) {
        entityManager.remove(entity);
    }

    public List<Plan> getAll() {
        return entityManager.createNamedQuery("Plan.getAllPlans", Plan.class).getResultList();
    }

    public Plan findPlanByName(String name) {
        Query query = entityManager.createNamedQuery("Plan.getPlanByName", Plan.class);
        query.setParameter("Name", name);
        return (Plan) query.getSingleResult();
    }
}
