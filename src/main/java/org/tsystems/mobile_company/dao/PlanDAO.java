package org.tsystems.mobile_company.dao;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.Plan;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sergey on 07.07.15.
 */
public class PlanDAO implements IEntityDAO<Plan> {

    private static PlanDAO planDAO;
    private EntityManager entityManager = EntityManagerFactoryInstance.getEntityManager();

    private PlanDAO() {

    }

    public static synchronized PlanDAO getInstance() {
        if (planDAO == null)
            planDAO = new PlanDAO();
        return planDAO;
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
}
