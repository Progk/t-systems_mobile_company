package org.tsystems.mobile_company.servlets;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entitys.PlanEntity;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by sergey on 29.06.15.
 */
public class ServletMain extends javax.servlet.http.HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getPathInfo();
        RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
        rd.forward(request, response);
    }
}
