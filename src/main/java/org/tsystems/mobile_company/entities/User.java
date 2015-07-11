package org.tsystems.mobile_company.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by sergey on 28.06.15.
 */
@NamedQueries({
        @NamedQuery(name="User.findUserByLoginAndPassword", query="FROM User u WHERE u.email=:Email AND u.password=:Password"),
        @NamedQuery(name="User.getAllUsers", query = "FROM User")
})

@Entity
@Table(name = "USER", catalog = "mobile_company")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Basic
    @Column(name = "SURNAME", nullable = false, length = 30)
    private String surname;

    @Basic
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dateOfBirth;

    @Basic
    @Column(name = "PASSPORT_DATA", nullable = false)
    private int passportData;

    @Basic
    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "USER_TYPE_ID", referencedColumnName = "ID")
    private UserType userType;


    /**
     * Contracts which have this user
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Contract> contracts;


    public User() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPassportData() {
        return passportData;
    }

    public void setPassportData(int passportData) {
        this.passportData = passportData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (passportData != that.passportData) return false;
        if (!name.equals(that.name)) return false;
        if (!surname.equals(that.surname)) return false;
        if (!dateOfBirth.equals(that.dateOfBirth)) return false;
        if (!address.equals(that.address)) return false;
        if (!email.equals(that.email)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + passportData;
        result = 31 * result + address.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
