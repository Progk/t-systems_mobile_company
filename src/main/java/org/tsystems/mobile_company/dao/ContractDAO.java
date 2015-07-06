package org.tsystems.mobile_company.dao;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.Contract;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sergey on 07.07.15.
 */
public class ContractDAO implements IEntityDAO<Contract> {

    private static ContractDAO contractDAO;
    private EntityManager entityManager = EntityManagerFactoryInstance.getEntityManager();

    private ContractDAO() {

    }

    public static synchronized ContractDAO getInstance() {
        if (contractDAO == null)
            contractDAO = new ContractDAO();
        return contractDAO;
    }

    public Contract addOrUpdate(Contract entity) {
        return entityManager.merge(entity);
    }

    public Contract find(int id) {
        return entityManager.find(Contract.class, id);
    }

    public void remove(Contract entity) {
        entityManager.remove(entity);
    }

    public List<Contract> getAll() {
        return entityManager.createNamedQuery("Contract.getAllContracts", Contract.class).getResultList();
    }
}
