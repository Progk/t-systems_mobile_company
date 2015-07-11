package org.tsystems.mobile_company.dao;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sergey on 07.07.15.
 */
public class OptionDAO implements IEntityDAO<Option> {

    private static OptionDAO optionDAOInstance;

    public static OptionDAO getInstance() {
        if (optionDAOInstance == null) {
            synchronized (ContractDAO.class) {
                if (optionDAOInstance == null)
                    optionDAOInstance = new OptionDAO();
            }
        }
        return optionDAOInstance;
    }

    private OptionDAO() {

    }


    public Option addOrUpdate(Option entity) {
        return EntityManagerFactoryInstance.getEntityManager().merge(entity);
    }

    public Option find(int id) {
        return EntityManagerFactoryInstance.getEntityManager().find(Option.class, id);
    }

    public void remove(Option entity) {
        EntityManagerFactoryInstance.getEntityManager().remove(entity);
    }

    public List<Option> getAll() {
        return EntityManagerFactoryInstance.getEntityManager().createNamedQuery("Option.getAllOptions", Option.class).getResultList();
    }



    public void deleteAllOptionsForContract(int id) {
        Query query = EntityManagerFactoryInstance.getEntityManager().createNamedQuery("Option.deleteAllOptionsForContract");
        query.setParameter("Id", id);
        query.executeUpdate();
    }
}
