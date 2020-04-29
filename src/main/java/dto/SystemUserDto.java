package dto;

import entity.Account;
import entity.SystemTransaction;
import entity.SystemUser;

import java.util.List;

public class SystemUserDto {
    private Long id;
    private String username;
    private String userpassword;
    private String name;
    private String surname;
    private Account account;
    private List<SystemTransaction> transactions;

    public SystemUserDto() {
    }

    public SystemUserDto(Long id, String username, String userpassword, String name, String surname, Account account, List<SystemTransaction> transactions) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
        this.surname = surname;
        this.account = account;
        this.transactions = transactions;
    }

    public SystemUser asEntity() {
        SystemUser user = new SystemUser();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setUserpassword(this.userpassword);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setAccount(this.account);
        user.setSystemTransactions(this.transactions);

        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<SystemTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<SystemTransaction> transactions) {
        this.transactions = transactions;
    }
}
