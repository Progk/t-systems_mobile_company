package org.tsystems.mobile_company.entitys;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sergey on 28.06.15.
 */
@Entity
@Table(name = "USER_TYPE", catalog = "mobile_company")
public class UserTypeEntity {

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
    private Set<UserEntity> users;

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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTypeEntity that = (UserTypeEntity) o;

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
