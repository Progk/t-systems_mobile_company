package org.tsystems.mobile_company;

import org.tsystems.mobile_company.dao.ContractDAO;
import org.tsystems.mobile_company.entities.*;
import org.tsystems.mobile_company.services.ContractServices;
import org.tsystems.mobile_company.services.UserServices;
import org.tsystems.mobile_company.utils.ECareException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by sergey on 04.07.15.
 * only for debugging
 */
public class Main {
    public static void main(String[] args) {
        try {
            Contract contract = ContractServices.getInstance().getContractByNumber("7111234532");
            contract.getSelectedOptions().clear();
            ContractServices.getInstance().updateContract(contract);
        } catch (ECareException e) {
            e.printStackTrace();
        }
    }
}
