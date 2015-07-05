package org.tsystems.mobile_company.filters;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.ValidatorFactoryInstance;
import org.tsystems.mobile_company.entitys.UserEntity;
import org.tsystems.mobile_company.validation.ValidationLogin;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by sergey on 02.07.15.
 */
@WebFilter(filterName = "FilterLoginValidation")
public class FilterLoginValidation implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String userMail = req.getParameter("user_mail");
        String userPassword = req.getParameter("user_pass");

        ValidationLogin validationLogin = new ValidationLogin(userMail, userPassword);
        Validator validator = ValidatorFactoryInstance.getValidator();
        Set<ConstraintViolation<ValidationLogin>> validationErrors = validator.validate(validationLogin);
        if (!validationErrors.isEmpty()) { //if input data is not valid
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        EntityManager entityManager = EntityManagerFactoryInstance.getEntityManager();
        try {
        entityManager.getTransaction().begin();
        TypedQuery<String> typedQuery = entityManager.createNamedQuery("UserEntity.checkLoginAndPassword", String.class);
        typedQuery.setParameter("Login", userMail);
        typedQuery.setParameter("Password", userPassword);
        if (typedQuery.getResultList().isEmpty()) { //if we have not user in db with this input data
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        } catch (Exception e) {

        } finally {
            entityManager.getTransaction().commit();
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
