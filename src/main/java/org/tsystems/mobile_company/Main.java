package org.tsystems.mobile_company;

import org.tsystems.mobile_company.entitys.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sergey on 04.07.15.
 * only for debugging
 */
public class Main {
    public static void main(String[] args) {
        EntityManager em = EntityManagerFactoryInstance.getEntityManager();
        em.getTransaction().begin();
        List<UserTypeEntity> userEntities = em.createQuery("from UserEntity ").getResultList();
        List<ContractEntity> contactEntities = em.createQuery("from ContractEntity ").getResultList();
        List<UserTypeEntity> userTypeEntities = em.createQuery("from UserTypeEntity ").getResultList();
        List<LockTypeEntity> lockTypeEntities = em.createQuery("from LockTypeEntity ").getResultList();
        List<OptionEntity> planEntities = em.createQuery("from OptionEntity ").getResultList();
        em.getTransaction().commit();
    }
}
