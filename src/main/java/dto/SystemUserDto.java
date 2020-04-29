package dto;

import entity.Account;
import entity.SystemTransaction;
import entity.SystemUser;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SystemUserDto implements Serializable {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final SystemUserDto dtoToCompare = (SystemUserDto) obj;

        return dtoToCompare.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        int hashConst = 50;
        hash = hashConst * hash + Objects.hashCode(this.id);
        hash = hashConst * hash + Objects.hashCode(this.username);
        hash = hashConst * hash + Objects.hashCode(this.userpassword);
        hash = hashConst * hash + Objects.hashCode(this.name);
        hash = hashConst * hash + Objects.hashCode(this.surname);
        return hash;
    }

    @Override
    public String toString() {
        return this.getId().toString();
    }
}
