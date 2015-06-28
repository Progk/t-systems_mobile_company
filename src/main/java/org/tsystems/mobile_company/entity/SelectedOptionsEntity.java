package org.tsystems.mobile_company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sergey on 28.06.15.
 */
@Entity
@Table(name = "SELECTED_OPTIONS", catalog = "mobile_company")
public class SelectedOptionsEntity {
    private int id;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectedOptionsEntity that = (SelectedOptionsEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
