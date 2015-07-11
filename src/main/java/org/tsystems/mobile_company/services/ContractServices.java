package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.dao.ContractDAO;
import org.tsystems.mobile_company.entities.Contract;
import org.tsystems.mobile_company.entities.LockType;
import org.tsystems.mobile_company.entities.User;
import org.tsystems.mobile_company.utils.ECareException;

import javax.persistence.NoResultException;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;

/**
 * Created by sergey on 07.07.15.
 */
public class ContractServices {

    private static ContractServices contractServices;
    private ContractDAO contractDAO = ContractDAO.getInstance();

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
        Contract c = contractDAO.addOrUpdate(contract);
        EntityManagerFactoryInstance.flush();
        EntityManagerFactoryInstance.commitTransaction();
    }

    public void changeLockType(Contract contract, boolean isAdmin) {
        if (isAdmin) {
            if (contract.getLockType().getId() == LockType.ADMIN)
                contract.getLockType().setId(LockType.NONE);
            else
                contract.getLockType().setId(LockType.ADMIN);
        } else {
            switch (contract.getLockType().getId()) {
                case LockType.USER:
                    contract.getLockType().setId(LockType.NONE);
                    break;
                case LockType.NONE:
                    contract.getLockType().setId(LockType.USER);
            }
        }
    }
}
