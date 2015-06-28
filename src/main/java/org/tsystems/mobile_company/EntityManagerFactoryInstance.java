package org.tsystems.mobile_company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Created by sergey on 27.06.15.
 */
public class EntityManagerFactoryInstance {
    private static EntityManagerFactory entityManagerFactory;
    public static String PERSISTENCE_UNIT_NAME = "mobile_company";

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManagerFactory getInstance() {
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    private EntityManagerFactoryInstance() {

    }

}
