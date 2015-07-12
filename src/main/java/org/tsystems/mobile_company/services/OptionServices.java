package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.dao.ContractDAO;
import org.tsystems.mobile_company.dao.OptionDAO;
import org.tsystems.mobile_company.dao.PlanDAO;
import org.tsystems.mobile_company.entities.Contract;
import org.tsystems.mobile_company.entities.Option;
import org.tsystems.mobile_company.entities.Plan;
import org.tsystems.mobile_company.entities.User;
import org.tsystems.mobile_company.utils.ECareException;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by sergey on 07.07.15.
 */
public class OptionServices {
    private static OptionServices optionServices;
    private OptionDAO optionDAO = OptionDAO.getInstance();
    private ContractDAO contractDAO = ContractDAO.getInstance();
    private PlanDAO planDAO = PlanDAO.getInstance();

    private OptionServices() {

    }

    public static synchronized OptionServices getInstance() {
        if (optionServices == null)
            optionServices = new OptionServices();
        return optionServices;
    }

    public List<Option> getAllOptions() {
        List<Option> optionList = null;
        EntityManagerFactoryInstance.beginTransaction();
        optionList = optionDAO.getAll();
        EntityManagerFactoryInstance.commitTransaction();
        return optionList;
    }

    public Option findOptionByName(String name) throws ECareException {
        Option o = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            o = optionDAO.findByName(name);
            EntityManagerFactoryInstance.commitTransaction();
        } catch (PersistenceException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("Error while finding options");
        }
        return o;
    }

    public Option findOptionById(int id) {
        Option option = null;
        EntityManagerFactoryInstance.beginTransaction();
        option = optionDAO.find(id);
        EntityManagerFactoryInstance.commitTransaction();
        return option;
    }


    /*public void deleteAllOptions() {
        List<Option> allOptions = getAllOptions();
        List<Contract> contractList = contractDAO.getAll();
        List<Plan> planList = planDAO.getAll();
        EntityManagerFactoryInstance.beginTransaction();

        for (Contract contract : contractList) {
            for (Option option : contract.getSelectedOptions()) {
                option.getContracts().clear();
            }
            contract.getSelectedOptions().clear();
            contractDAO.addOrUpdate(contract);
        }

        for (Plan plan : planList) {
            for (Option option : plan.getOptions()) {
                option.getOptions().clear();
            }
            plan.getOptions().clear();
            planDAO.addOrUpdate(plan);
        }

        for (Option option : allOptions) {
            for (Option option2 : option.getLocked()) {
                option2.getLocked().clear();
            }
            option.getOptions().clear();
            optionDAO.addOrUpdate(option);
        }

        for (Option option : allOptions) {
            option.getContracts().clear();
            option.getPlans().clear();
            option.getOptions().clear();
            option.getLocked().clear();
            optionDAO.addOrUpdate(option);
        }
        allOptions.clear();
        EntityManagerFactoryInstance.commitTransaction();
    }
*/
    /*public void deleteAllOptions(Contract contract) {
        EntityManagerFactoryInstance.beginTransaction();
        List<Option> allPlans = OptionDAO.getInstance().getAll();
        for (Option option: allPlans) {
            //EntityManagerFactoryInstance.getEntityManager().merge(option);
            option.getContracts().remove(contract);
            optionDAO.addOrUpdate(option);
        }

        for (Option option: contract.getSelectedOptions()) {
            option.getContracts().remove(contract);
        }
        contract.getSelectedOptions().clear();
        //EntityManagerFactoryInstance.getEntityManager().merge(contract);
        contractDAO.addOrUpdate(contract);
        EntityManagerFactoryInstance.commitTransaction();
    }*/

    public void deleteOption(String name) {
        EntityManagerFactoryInstance.beginTransaction();
        optionDAO.deleteOptionByName(name);
        EntityManagerFactoryInstance.commitTransaction();
    }


}
