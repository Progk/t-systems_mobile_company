package org.tsystems.mobile_company.entitys;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergey on 28.06.15.
 */
@Entity
@Table(name = "CONTRACT", catalog = "mobile_company")
public class ContractEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    /**
     * number of phone
     * format - 7 111 222 33 44
     */
    @Basic
    @Column(name = "NUMBER", nullable = false, length = 11)
    private String number;

    /**
     * user which has this contract
     */
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    /**
     * type of lock
     */
    @ManyToOne
    @JoinColumn(name = "LOCK_TYPE_ID")
    private LockTypeEntity lockType;

    /**
     * selected options for this contract
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="SELECTED_OPTIONS",
            joinColumns={@JoinColumn(name="CONTRACT_ID")},
            inverseJoinColumns={@JoinColumn(name="OPTION_ID")})
    private Set<OptionEntity> selectedOptions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LockTypeEntity getLockType() {
        return lockType;
    }

    public void setLockType(LockTypeEntity lockType) {
        this.lockType = lockType;
    }

    public Set<OptionEntity> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(Set<OptionEntity> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractEntity that = (ContractEntity) o;

        if (id != that.id) return false;
        if (!number.equals(that.number)) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number.hashCode();
        return result;
    }
}
