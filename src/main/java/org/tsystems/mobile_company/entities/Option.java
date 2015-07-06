package org.tsystems.mobile_company.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergey on 28.06.15.
 */

@NamedQueries({
        @NamedQuery(name="Option.getAllOptions", query = "FROM Option")
})

@Entity
@Table(name = "OPTION", catalog = "mobile_company")
public class Option {

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
    private Set<Plan> plans = new HashSet<Plan>();

    /**
     * contracts which include this option
     */
    @ManyToMany(mappedBy="selectedOptions")
    private Set<Contract> contracts = new HashSet<Contract>();

    /**
     * locked options for this option
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="OPTIONS_CONSTRAINT",
            joinColumns={@JoinColumn(name="OPTION_ID")},
            inverseJoinColumns={@JoinColumn(name="OPTION_LOCKED_ID")})
    private Set<Option> locked = new HashSet<Option>();

    /**
     * options for locked option
     */
    @ManyToMany(mappedBy="locked")
    private Set<Option> options;


    public Option(String name, int price, int costConnect) {
        this.name = name;
        this.price = price;
        this.costConnect = costConnect;
    }

    public Option() {
    }

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

    public Set<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }

    public Set<Option> getLocked() {
        return locked;
    }

    public void setLocked(Set<Option> locked) {
        this.locked = locked;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option that = (Option) o;

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
