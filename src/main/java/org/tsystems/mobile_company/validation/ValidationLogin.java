package org.tsystems.mobile_company.validation;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by sergey on 02.07.15.
 */
public class ValidationLogin {

    @NotNull
    @NotEmpty
    @Email
    @Length(max=50)
    private String login;

    @NotNull
    @NotEmpty
    @Length(max=100)
    private String password;

    public ValidationLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
