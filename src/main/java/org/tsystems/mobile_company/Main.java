package org.tsystems.mobile_company;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.tsystems.mobile_company.entity.PlanEntity;
import javax.persistence.EntityManager;
import java.util.ArrayList;

/**
 * Created by sergey on 27.06.15.
 */
public class Main {

    //test
    public static void main(final String[] args) throws Exception {
        final EntityManager em = EntityManagerFactoryInstance.getEntityManager();
        Transaction tx = null;


        try {
            em.getTransaction().begin();

            ArrayList<PlanEntity> q = (ArrayList<PlanEntity>) em.createQuery("from PlanEntity ").getResultList();
            for (PlanEntity p: q) {
                System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
