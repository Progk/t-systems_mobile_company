package org.tsystems.mobile_company.dao;

import org.tsystems.mobile_company.EntityManagerFactoryInstance;
import org.tsystems.mobile_company.entities.Option;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sergey on 07.07.15.
 */
public class OptionDAO implements IEntityDAO<Option> {

    private EntityManager entityManager = EntityManagerFactoryInstance.getEntityManager();

    public Option addOrUpdate(Option entity) {
        return entityManager.merge(entity);
    }

    public Option find(int id) {
        return entityManager.find(Option.class, id);
    }

    public void remove(Option entity) {
        entityManager.remove(entity);
    }

    public List<Option> getAll() {
        return entityManager.createNamedQuery("Option.getAllOptions", Option.class).getResultList();
    }
}
