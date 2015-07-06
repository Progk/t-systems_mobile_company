package org.tsystems.mobile_company.dao;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.User;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sergey on 06.07.15.
 */
public class UserDAO implements IEntityDAO<User> {

    private static UserDAO userDAO;
    private EntityManager entityManager = EntityManagerFactoryInstance.getEntityManager();

    private UserDAO() {

    }

    public static synchronized UserDAO getInstance() {
        if (userDAO == null)
            userDAO = new UserDAO();
        return userDAO;
    }

    public User addOrUpdate(User entity) {
        return entityManager.merge(entity);
    }

    public User find(int id) {
        return entityManager.find(User.class, id);
    }

    public void remove(User entity) {
        entityManager.remove(entity);
    }

    public List<User> getAll() {
        return entityManager.createNamedQuery("User.getAllUsers", User.class).getResultList();
    }
}
