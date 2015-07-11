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

    public static final int ADMIN_TYPE = 1;
    public static final int SIMPLE_USER_TYPE =  2;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    /**
     * Name
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    /**
     * Surname
     */
    @Basic
    @Column(name = "SURNAME", nullable = false, length = 30)
    private String surname;

    /**
     * Date of birth
     */
    @Basic
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dateOfBirth;

    /**
     * Passport data
     */
    @Basic
    @Column(name = "PASSPORT_DATA", nullable = false)
    private int passportData;

    /**
     * Address
     */
    @Basic
    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    /**
     * Email
     */
    @Basic
    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    /**
     * Password
     */
    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    /**
     * User type id
     */
    @Basic
    @Column(name = "USER_TYPE_ID", nullable = false, length = 10)
    private int userType;


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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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



    public boolean isAdminType() {
        if (userType == ADMIN_TYPE)
            return true;
        return false;
    }

    public boolean isSimpleUserType() {
        if (userType == SIMPLE_USER_TYPE)
            return true;
        return false;
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
