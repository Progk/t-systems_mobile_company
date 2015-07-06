package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.User;
import org.tsystems.mobile_company.entities.UserType;

import javax.persistence.EntityManager;
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
            TypedQuery<User> typedQuery = entityManager.createNamedQuery("UserEntity.checkLoginAndPassword",
                    User.class);
            typedQuery.setParameter("Login", name);
            typedQuery.setParameter("Password", password);
            if (!typedQuery.getResultList().isEmpty() && typedQuery.getResultList().size() == 1) {
                if (typedQuery.getResultList().get(0).getUserType().getType().equals(UserType.ADMIN_TYPE))
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
