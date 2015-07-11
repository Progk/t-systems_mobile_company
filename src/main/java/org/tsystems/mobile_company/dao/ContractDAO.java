package org.tsystems.mobile_company.dao;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sergey on 07.07.15.
 */
public class ContractDAO implements IEntityDAO<Contract> {

    private static volatile ContractDAO contractDAOInstance;

   public static ContractDAO getInstance() {
       if (contractDAOInstance == null) {
           synchronized (ContractDAO.class) {
               if (contractDAOInstance == null)
                   contractDAOInstance = new ContractDAO();
           }
       }
        return contractDAOInstance;
   }

    public Contract addOrUpdate(Contract entity) {
        return EntityManagerFactoryInstance.getEntityManager().merge(entity);
    }

    public Contract find(int id) {
        return EntityManagerFactoryInstance.getEntityManager().find(Contract.class, id);
    }

    public void remove(Contract entity) {
        EntityManagerFactoryInstance.getEntityManager().remove(entity);
    }

    public List<Contract> getAll() {
        return EntityManagerFactoryInstance.getEntityManager().createNamedQuery("Contract.getAllContracts", Contract.class).getResultList();
    }

    public Contract findContractByNumber(String number) {
        Query query = EntityManagerFactoryInstance.getEntityManager().createNamedQuery("Contract.findContractByNumber", Contract.class);
        query.setParameter("Number", number);
        return (Contract) query.getSingleResult();
    }
}
