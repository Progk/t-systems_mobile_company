package org.tsystems.mobile_company.entitys;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sergey on 28.06.15.
 */
@Entity
@Table(name = "LOCK_TYPE", catalog = "mobile_company")
public class LockTypeEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "TYPE", nullable = false, length = 20)
    private String type;

    /**
     * contracts which have this lock type
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lockType")
    private Set<ContractEntity> contracts;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockTypeEntity that = (LockTypeEntity) o;

        if (id != that.id) return false;
        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type.hashCode();
        return result;
    }
}
