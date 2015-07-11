package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.dao.ContractDAO;
import org.tsystems.mobile_company.dao.OptionDAO;
import org.tsystems.mobile_company.entities.Contract;
import org.tsystems.mobile_company.entities.Option;
import org.tsystems.mobile_company.entities.User;
import org.tsystems.mobile_company.utils.ECareException;

import javax.persistence.NoResultException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Created by sergey on 07.07.15.
 */
public class ContractServices {

    private static ContractServices contractServices;
    private ContractDAO contractDAO = ContractDAO.getInstance();
    private OptionDAO optionDAO = OptionDAO.getInstance();

    private ContractServices() {

    }

    public static synchronized ContractServices getInstance() {
        if (contractServices == null)
            contractServices = new ContractServices();
        return contractServices;
    }

    public Contract getContractByNumber(String number) throws ECareException {
        Contract contract = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            contract = contractDAO.findContractByNumber(number);
            EntityManagerFactoryInstance.commitTransaction();
        } catch (NoResultException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("No user with this Email and Password");
        }
        return contract;
    }

    public void updateContract(Contract contract) {
        EntityManagerFactoryInstance.beginTransaction();
        contractDAO.addOrUpdate(contract);
        EntityManagerFactoryInstance.commitTransaction();
    }

    public void changeLockType(Contract contract, boolean isAdmin) {
        if (isAdmin) {
            if (contract.isLockedByAdmin())
                contract.setLockTypeId(Contract.LOCKED_NONE);
            else
                contract.setLockTypeId(Contract.LOCKED_ADMIN);
        } else {
            switch (contract.getLockTypeId()) {
                case Contract.LOCKED_USER:
                    contract.setLockTypeId(Contract.LOCKED_NONE);
                    break;
                case Contract.LOCKED_NONE:
                    contract.setLockTypeId(Contract.LOCKED_USER);;
            }
        }
    }

    public void deleteAllOptions(Contract contract) {
        EntityManagerFactoryInstance.beginTransaction();
        List<Option> allPlans = OptionDAO.getInstance().getAll();
        for (Option option: allPlans) {
            //EntityManagerFactoryInstance.getEntityManager().merge(option);
            optionDAO.addOrUpdate(option);
        }

        for (Option option: contract.getSelectedOptions()) {
            option.getContracts().remove(contract);
        }
        contract.getSelectedOptions().clear();
        //EntityManagerFactoryInstance.getEntityManager().merge(contract);
        contractDAO.addOrUpdate(contract);
        EntityManagerFactoryInstance.commitTransaction();
    }
}
