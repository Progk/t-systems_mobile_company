package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.dao.UserDAO;
import org.tsystems.mobile_company.entities.User;
import org.tsystems.mobile_company.entities.UserType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Created by sergey on 07.07.15.
 */
public class UserServices {

    private static UserServices userServices;
    private UserDAO userDAO;

    private UserServices() {
        userDAO = new UserDAO();
    }

    public static synchronized UserServices getInstance() {
        if (userServices == null)
            userServices = new UserServices();
        return userServices;
    }

    public User findUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            user = userDAO.findByEmailAndPassword(email, password);
            EntityManagerFactoryInstance.commitTransaction();
        } catch (NoResultException e) {
            e.printStackTrace(); //need some custom exception here
        }
        return user;
    }
}
