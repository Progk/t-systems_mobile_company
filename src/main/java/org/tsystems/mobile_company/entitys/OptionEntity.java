package org.tsystems.mobile_company.entitys;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergey on 28.06.15.
 */
@Entity
@Table(name = "OPTION", catalog = "mobile_company")
public class OptionEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Basic
    @Column(name = "PRICE", nullable = false)
    private int price;

    @Basic
    @Column(name = "COST_CONNECT", nullable = false)
    private int costConnect;

    /**
     * plans which include this option
     */
    @ManyToMany(mappedBy="options")
    private Set<PlanEntity> plans = new HashSet<PlanEntity>();

    /**
     * contracts which include this option
     */
    @ManyToMany(mappedBy="selectedOptions")
    private Set<ContractEntity> contracts = new HashSet<ContractEntity>();

    /**
     * locked options for this option
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="OPTIONS_CONSTRAINT",
            joinColumns={@JoinColumn(name="OPTION_ID")},
            inverseJoinColumns={@JoinColumn(name="OPTION_LOCKED_ID")})
    private Set<OptionEntity> locked = new HashSet<OptionEntity>();

    /**
     * options for locked option
     */
    @ManyToMany(mappedBy="locked")
    private Set<OptionEntity> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCostConnect() {
        return costConnect;
    }

    public void setCostConnect(int costConnect) {
        this.costConnect = costConnect;
    }

    public Set<PlanEntity> getPlans() {
        return plans;
    }

    public void setPlans(Set<PlanEntity> plans) {
        this.plans = plans;
    }

    public Set<OptionEntity> getLocked() {
        return locked;
    }

    public void setLocked(Set<OptionEntity> locked) {
        this.locked = locked;
    }

    public Set<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(Set<OptionEntity> options) {
        this.options = options;
    }

    public Set<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(Set<ContractEntity> contracts) {
        this.contracts = contracts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionEntity that = (OptionEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (costConnect != that.costConnect) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        result = 31 * result + costConnect;
        return result;
    }
}
