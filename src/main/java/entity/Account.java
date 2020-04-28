package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private int balance;

    @NotNull
    private String currency;

    @NotNull
    @OneToOne
    private SystemUser systemUser;

    public Account() {
    }


    /**
     * Users account
     *
     * @param balance    Remaining balance
     * @param currency   Selected currency for account see {@link Currency}
     * @param systemUser The associated {@link SystemUser}
     */
    public Account(@NotNull int balance, @NotNull String currency, @NotNull SystemUser systemUser) {
        this.balance = balance;
        this.currency = currency;
        this.systemUser = systemUser;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
}
