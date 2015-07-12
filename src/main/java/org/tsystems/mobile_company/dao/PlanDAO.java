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
        return EntityManagerFactoryInstance.getEntityManager().merge(entity);
    }


    public Plan find(int id) {
        return EntityManagerFactoryInstance.getEntityManager().find(Plan.class, id);
    }


    public void remove(Plan entity) {
        EntityManagerFactoryInstance.getEntityManager().remove(entity);
    }


    public List<Plan> getAll() {
        return EntityManagerFactoryInstance.getEntityManager().createNamedQuery("Plan.getAllPlans", Plan.class).getResultList();
    }

    public Plan load(int id) {
        return EntityManagerFactoryInstance.getEntityManager().find(Plan.class, id);
    }

    /**
     * Find Plan By Name
     * @param name of plan
     * @return Plan if exist, otherwise NoResultException
     */
    public Plan findPlanByName(String name) {
        Query query = EntityManagerFactoryInstance.getEntityManager().createNamedQuery("Plan.getPlanByName", Plan.class);
        query.setParameter("Name", name);
        return (Plan) query.getSingleResult();
    }
}
