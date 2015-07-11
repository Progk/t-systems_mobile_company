package org.tsystems.mobile_company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 * Created by sergey on 27.06.15.
 */
public class EntityManagerFactoryInstance {
    private static EntityManagerFactory entityManagerFactory;
    private static  ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();


    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("mobile_company");
    }

    private EntityManagerFactoryInstance() {

    }

    public static EntityManager getEntityManager() {
        EntityManager entityManager = threadLocal.get();
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
            threadLocal.set(entityManager);
        }

        return entityManager;
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void flush() {
        getEntityManager().flush();
    }

    public static void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        getEntityManager().getTransaction().rollback();
    }

    public static boolean isActiveTransaction() {
        return getEntityManager().getTransaction().isActive();
    }



}
