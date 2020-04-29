package dto;

import entity.Account;
import entity.Currency;
import entity.SystemUser;

import javax.validation.constraints.NotNull;

public class AccountDto {

    private Long id;

    private int balance;

    private String currency;

    private SystemUser systemUser;

    public AccountDto() {
    }

    /**
     * Users account
     *
     * @param balance    Remaining balance
     * @param currency   Selected currency for account see {@link Currency}
     * @param systemUser The associated {@link SystemUser}
     */
    public AccountDto(@NotNull int balance, @NotNull String currency, @NotNull SystemUser systemUser) {
        this.balance = balance;
        this.currency = currency;
        this.systemUser = systemUser;
    }

    public Account asEntity() {
        Account account = new Account();
        account.setId(this.id);
        account.setBalance(this.balance);
        account.setCurrency(this.currency);
        account.setSystemUser(this.systemUser);

        return account;

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
