package org.tsystems.mobile_company.entitys;

import javax.persistence.*;

/**
 * Created by sergey on 28.06.15.
 */
@Entity
@Table(name = "OPTION", catalog = "mobile_company")
public class OptionEntity {
    private int id;
    private String name;
    private int price;
    private int costConnect;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PRICE")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "COST_CONNECT")
    public int getCostConnect() {
        return costConnect;
    }

    public void setCostConnect(int costConnect) {
        this.costConnect = costConnect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionEntity that = (OptionEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (costConnect != that.costConnect) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + costConnect;
        return result;
    }
}
