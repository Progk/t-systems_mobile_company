package org.tsystems.mobile_company.dao;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sergey on 06.07.15.
 */
public class UserDAO implements IEntityDAO<User> {

    private EntityManager entityManager = EntityManagerFactoryInstance.getEntityManager();
    private static UserDAO userDAOInstance;

    public static UserDAO getInstance() {
        if (userDAOInstance == null) {
            synchronized (ContractDAO.class) {
                if (userDAOInstance == null)
                    userDAOInstance = new UserDAO();
            }
        }
        return userDAOInstance;
    }

    private UserDAO() {

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

    public User findByEmailAndPassword(String email, String password) {
        Query query = entityManager.createNamedQuery("User.findUserByLoginAndPassword", User.class);
        query.setParameter("Email", email);
        query.setParameter("Password", password);
        return (User) query.getSingleResult();
    }
}
