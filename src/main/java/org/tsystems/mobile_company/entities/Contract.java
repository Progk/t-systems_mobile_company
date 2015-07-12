package org.tsystems.mobile_company.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sergey on 28.06.15.
 */
@NamedQueries({
        @NamedQuery(name="Contract.getAllContracts", query = "FROM Contract"),
        @NamedQuery(name="Contract.findContractByNumber", query = "FROM Contract WHERE number=:Number")
})


@Entity
@Table(name = "CONTRACT", catalog = "mobile_company")
public class Contract implements Serializable {

    public static final int LOCKED_ADMIN = 1;
    public static final int LOCKED_USER = 2;
    public static final int LOCKED_NONE = 3;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    /**
     * Number of phone
     * format - 7 111 222 33 44
     */
    @Basic
    @Column(name = "NUMBER", nullable = false, length = 11)
    private String number;

    /**
     * Plan Id
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PLAN_ID", referencedColumnName = "ID")
    private Plan planId;

    /**
     * User which has this contract
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    /**
     * Type of locked
     */
    @Basic
    @Column(name = "LOCK_TYPE_ID", nullable = false)
    private int lockTypeId;



    /**
     * Selected options for this contract
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="SELECTED_OPTIONS",
            joinColumns={@JoinColumn(name="CONTRACT_ID")},
            inverseJoinColumns={@JoinColumn(name="OPTION_ID")})
    private List<Option> selectedOptions;

    public Contract() {
    }

    public Contract(String number, Plan planId, User user, int lockTypeId) {
        this.number = number;
        this.planId = planId;
        this.user = user;
        this.lockTypeId = lockTypeId;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLockTypeId() {
        return lockTypeId;
    }

    public boolean isLockedByAdmin() {
        if (lockTypeId == LOCKED_ADMIN)
            return true;
        return false;
    }

    public boolean isLockedByUser() {
        if (lockTypeId == LOCKED_USER)
            return true;
        return false;
    }

    public boolean isNotLocked() {
        if (lockTypeId == LOCKED_NONE)
            return true;
        return false;
    }


    public void setLockTypeId(int lockTypeId) {
        this.lockTypeId = lockTypeId;
    }

    public List<Option> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<Option> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }


    public Plan getPlanId() {
        return planId;
    }

    public void setPlanId(Plan planId) {
        this.planId = planId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract that = (Contract) o;

        if (id != that.id) return false;
        if (!number.equals(that.number)) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result;
        return result;
    }
}
