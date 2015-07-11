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

    @Basic
    @Column(name = "PLAN_ID", nullable = false)
    private int planId;

    /**
     * user which has this contract
     */
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    /**
     * type of lock
     */
    @ManyToOne
    @JoinColumn(name = "LOCK_TYPE_ID", referencedColumnName = "ID")
    private LockType lockType;



    /**
     * selected options for this contract
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="SELECTED_OPTIONS",
            joinColumns={@JoinColumn(name="CONTRACT_ID")},
            inverseJoinColumns={@JoinColumn(name="OPTION_ID")})

    private List<Option> selectedOptions;

    public Contract() {
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LockType getLockType() {
        return lockType;
    }

    public void setLockType(LockType lockType) {
        this.lockType = lockType;
    }

    public List<Option> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<Option> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }


    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
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
