package org.tsystems.mobile_company.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sergey on 28.06.15.
 */
@Entity
@Table(name = "USER_TYPE", catalog = "mobile_company")
public class UserType {
    public static final int ADMIN_TYPE = 1;
    public static final int USER_TYPE =  2;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "TYPE", nullable = false, length = 20)
    private String type;

    /**
     * users which have this lock type
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userType")
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserType that = (UserType) o;

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
