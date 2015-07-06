package org.tsystems.mobile_company;

import org.tsystems.mobile_company.dao.ContractDAO;
import org.tsystems.mobile_company.dao.OptionDAO;
import org.tsystems.mobile_company.dao.UserDAO;
import org.tsystems.mobile_company.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sergey on 04.07.15.
 * only for debugging
 */
public class Main {
    public static void main(String[] args) {
        EntityManager em = EntityManagerFactoryInstance.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Contract c = new Contract("123", 2, 3, 2);
        ContractDAO.getInstance().addOrUpdate(c);
        et.commit();
    }
}
