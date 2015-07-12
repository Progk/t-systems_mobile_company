package org.tsystems.mobile_company.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sergey on 28.06.15.
 */
@NamedQueries({
        @NamedQuery(name="Plan.getAllPlans", query = "FROM Plan"),
        @NamedQuery(name="Plan.getPlanByName", query = "FROM Plan WHERE name=:Name"),
        @NamedQuery (name = "Plan.deleteAllPlans", query = "DELETE FROM Plan")
})


@Entity
@Table(name = "PLAN", catalog = "mobile_company")
public class Plan {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    /**
     * Name of plan
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    /**
     * Price of plan
     */
    @Basic
    @Column(name = "PRICE")
    private int price;

    /**
     * Available options for this plan
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="PLAN_OPTIONS",
            joinColumns={@JoinColumn(name="PLAN_ID")},
            inverseJoinColumns={@JoinColumn(name="OPTION_ID")})
    private List<Option> options;

    public Plan(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Plan() {
    }

    public Plan(String name, int price, List<Option> options) {
        this.name = name;
        this.price = price;
        this.options = options;
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan that = (Plan) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        return result;
    }
}
