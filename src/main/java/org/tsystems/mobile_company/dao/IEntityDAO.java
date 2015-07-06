package org.tsystems.mobile_company.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by sergey on 06.07.15.
 */
public interface IEntityDAO<T> {

    /**
     * Add new row to db if not exist or update
     * @return
     */
    public T addOrUpdate(T entity);

    /**
     * Returns
     * @param id
     * @return Object with this id
     */
    public T find(int id);

    /**
     * Delete object
     * @param entity
     */
    public void remove(T entity);

    /**
     *
     * @return List of all entity
     * @throws SQLException
     */
    public List<T> getAll() throws SQLException;
}
