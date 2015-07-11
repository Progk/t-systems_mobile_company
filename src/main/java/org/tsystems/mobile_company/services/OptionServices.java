package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.dao.OptionDAO;
import org.tsystems.mobile_company.utils.ECareException;

import javax.persistence.PersistenceException;

/**
 * Created by sergey on 07.07.15.
 */
public class OptionServices {
    private static OptionServices optionServices;
    private OptionDAO optionDAO = OptionDAO.getInstance();

    private OptionServices() {

    }

    public static synchronized OptionServices getInstance() {
        if (optionServices == null)
            optionServices = new OptionServices();
        return optionServices;
    }

    public void deleteAllOptionsForContract(int contractId) throws ECareException {
        try {
            EntityManagerFactoryInstance.beginTransaction();
            optionDAO.deleteAllOptionsForContract(contractId);
            EntityManagerFactoryInstance.commitTransaction();
        } catch (PersistenceException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("Error while deleting options");
        }
    }
}
