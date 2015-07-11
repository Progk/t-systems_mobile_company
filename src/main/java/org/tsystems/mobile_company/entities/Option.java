package org.tsystems.mobile_company.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sergey on 28.06.15.
 */

@NamedQueries({
        @NamedQuery(name="Option.getAllOptions", query = "FROM Option"),
        @NamedQuery(name="Option.deleteAllOptionsForContract", query = "DELETE FROM Option WHERE id=:Id")
})

@Entity
@Table(name = "OPTION", catalog = "mobile_company")
public class Option {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    /**
     * Name of optin
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    /**
     * Price of option
     */
    @Basic
    @Column(name = "PRICE", nullable = false)
    private int price;

    /**
     * Coost connection
     */
    @Basic
    @Column(name = "COST_CONNECT", nullable = false)
    private int costConnect;

    /**
     * Plans which include this option
     */
    @ManyToMany(mappedBy="options", cascade = {CascadeType.ALL})
    private List<Plan> plans;

    /**
     * Contracts which include this option
     */
    @ManyToMany(mappedBy="selectedOptions")
    private List<Contract> contracts;

    /**
     * Locked options for this option
     */
    @ManyToMany
    @JoinTable(name="OPTIONS_CONSTRAINT",
            joinColumns={@JoinColumn(name="OPTION_ID")},
            inverseJoinColumns={@JoinColumn(name="OPTION_LOCKED_ID")})
    private List<Option> locked;

    /**
     * Options for locked option
     */
    @ManyToMany(mappedBy="locked", cascade = {CascadeType.ALL})
    private List<Option> options;


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

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public List<Option> getLocked() {
        return locked;
    }

    public void setLocked(List<Option> locked) {
        this.locked = locked;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
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
