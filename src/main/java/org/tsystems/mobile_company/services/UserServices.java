package org.tsystems.mobile_company.services;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.dao.UserDAO;
import org.tsystems.mobile_company.entities.*;
import org.tsystems.mobile_company.utils.ECareException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sergey on 07.07.15.
 */
public class UserServices {

    private static UserServices userServices;
    private UserDAO userDAO = UserDAO.getInstance();

    private UserServices() {

    }

    public static synchronized UserServices getInstance() {
        if (userServices == null)
            userServices = new UserServices();
        return userServices;
    }

    public User findUserByEmailAndPassword(String email, String password) throws ECareException {
        User user = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            user = userDAO.findByEmailAndPassword(email, password);
            EntityManagerFactoryInstance.commitTransaction();
        } catch (NoResultException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("No user with this Email and Password");
        }
        return user;
    }

    public User addUser(String name, String surname, String dateOfBirth, String passport, String address, String email, String password, boolean isAdmin) {
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth).getTime());
        } catch (ParseException e) {
            date = new Date(System.currentTimeMillis());
        }
        User user = new User(name, surname, date, Integer.valueOf(passport), address, email, password, User.SIMPLE_USER_TYPE);
        if (isAdmin)
            user.setUserType(User.ADMIN_TYPE);
        EntityManagerFactoryInstance.beginTransaction();
        user = userDAO.addOrUpdate(user);
        EntityManagerFactoryInstance.commitTransaction();
        return user;
    }

    public void findAvailableOptions(Map<Option, Boolean> options, String[] selectedOptions) {
        Set<Option> planOptions = options.keySet();
        for(Map.Entry<Option, Boolean> entry : options.entrySet())
            entry.setValue(false);
        if (selectedOptions != null) {
            List<String> selectedOptionsList = Arrays.asList(selectedOptions);
            for (Option option : planOptions) {
                if (selectedOptionsList.contains(option.getName())) {
                    for (Option blockOption : option.getLocked()) {
                        if (options.containsKey(blockOption))
                            options.put(blockOption, true);
                    }
                }
            }
        }
    }

    public List<User> getAll() throws ECareException {
        List<User> user = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            user = userDAO.getAll();
            EntityManagerFactoryInstance.commitTransaction();
        } catch (IllegalArgumentException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("Error while read users");
        }
        return user;
    }

    public List<User> getAllSimpleUser() throws ECareException {
        List<User> user = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            user = userDAO.getAll();
            EntityManagerFactoryInstance.commitTransaction();
        } catch (IllegalArgumentException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("Error while read users");
        }
        if (user != null) {
            Iterator<User> iterator = user.iterator();
            while (iterator.hasNext()) {
                User u = iterator.next();
                if (u.isAdminType())
                    iterator.remove();
            }
        }
        return user;
    }

    public Map<User, Boolean> getAllSimpleUserWithLockType() throws ECareException {
        Map<User, Boolean> simpleUserWithLockTypeMap = new HashMap<>();
        List<User> user = null;
        try {
            EntityManagerFactoryInstance.beginTransaction();
            user = userDAO.getAll();
            EntityManagerFactoryInstance.commitTransaction();
        } catch (IllegalArgumentException e) {
            if (EntityManagerFactoryInstance.isActiveTransaction())
                EntityManagerFactoryInstance.rollbackTransaction();
            throw new ECareException("Error while read users");
        }
        if (user != null) {
            Iterator<User> iterator = user.iterator();
            while (iterator.hasNext()) {
                User u = iterator.next();
                if (u.isAdminType()) {
                    iterator.remove();
                    continue;
                }
                boolean lock = true;
                for (Contract c : u.getContracts()) {
                    if (c.isLockedByAdmin()) {
                        lock = false;
                        break;
                    }

                }
                simpleUserWithLockTypeMap.put(u, lock);
            }
        }
        return simpleUserWithLockTypeMap;
    }


    public User findUserByEmail(String email) {
        User user = null;
        EntityManagerFactoryInstance.beginTransaction();
        user = userDAO.findUserByEmail(email);
        EntityManagerFactoryInstance.commitTransaction();
        return user;
    }


}
