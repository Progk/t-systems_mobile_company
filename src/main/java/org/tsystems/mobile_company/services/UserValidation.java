package org.tsystems.mobile_company.services;

import org.hibernate.Transaction;
import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entitys.UserEntity;
import org.tsystems.mobile_company.entitys.UserTypeEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Created by sergey on 06.07.15.
 */
public class UserValidation {
    private String name;
    private String password;
    private boolean isAdmin;

    public UserValidation(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     *
     * @return true if user with this data exist, otherwise false
     */
    public boolean validate() {
        EntityManager entityManager = EntityManagerFactoryInstance.getEntityManager();
        boolean returnState = false;
        try {
            TypedQuery<UserEntity> typedQuery = entityManager.createNamedQuery("UserEntity.checkLoginAndPassword",
                    UserEntity.class);
            typedQuery.setParameter("Login", name);
            typedQuery.setParameter("Password", password);
            if (!typedQuery.getResultList().isEmpty() && typedQuery.getResultList().size() == 1) {
                if (typedQuery.getResultList().get(0).getUserType().getType().equals(UserTypeEntity.ADMIN_TYPE))
                    isAdmin = true;
                returnState = true;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return returnState;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
