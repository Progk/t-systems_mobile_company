package org.tsystems.mobile_company.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sergey on 28.06.15.
 */
@Entity
@Table(name = "LOCK_TYPE", catalog = "mobile_company")
public class LockType {

    public static final int ADMIN = 1;
    public static final int USER = 2;
    public static final int NONE = 3;

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
    private List<Contract> contracts;


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

        LockType that = (LockType) o;

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
