package org.tsystems.mobile_company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Created by sergey on 27.06.15.
 */
public class EntityManagerFactoryInstance {
    public static String PERSISTENCE_UNIT_NAME = "mobile_company";
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    /*public static EntityManagerFactory getInstance() {
        return entityManagerFactory;
    }*/

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    private EntityManagerFactoryInstance() {

    }

}
