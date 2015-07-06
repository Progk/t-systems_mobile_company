package org.tsystems.mobile_company;

import org.tsystems.mobile_company.entitys.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by sergey on 04.07.15.
 * only for debugging
 */
public class Main {
    public static void main(String[] args) {
        EntityManager em = EntityManagerFactoryInstance.getEntityManager();
        em.getTransaction().begin();
        List<UserEntity> userEntities = em.createQuery("from UserEntity ").getResultList();
        List<ContractEntity> contactEntities = em.createQuery("from ContractEntity ").getResultList();
        List<UserTypeEntity> userTypeEntities = em.createQuery("from UserTypeEntity ").getResultList();
        List<LockTypeEntity> lockTypeEntities = em.createQuery("from LockTypeEntity ").getResultList();
        List<OptionEntity> planEntities = em.createQuery("from OptionEntity ").getResultList();
        em.getTransaction().commit();
        em = EntityManagerFactoryInstance.getEntityManager();
        TypedQuery<UserEntity> typedQuery = em.createNamedQuery("UserEntity.checkLoginAndPassword",
                UserEntity.class);
        typedQuery.setParameter("Login", "ivan@gmail.com");
        typedQuery.setParameter("Password", "iamivan");
        UserEntity u = typedQuery.getResultList().get(0);
        em.getTransaction().begin();
        UserEntity uu = em.merge(u);
        uu.setName("Ivan");
        em.getTransaction().commit();
        em.close();
    }
}
