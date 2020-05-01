package dto;

import entity.Account;
import entity.SystemTransaction;
import entity.SystemUser;
import entity.SystemUserGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SystemUserDto implements Serializable {
    private Long id;
    private String username;
    private String userpassword;
    private String name;
    private String surname;
    private AccountDto account;
    private List<SystemTransactionDto> transactions;
    private SystemUserGroup userGroup;
    
    public SystemUserDto() {
    }

    public SystemUserDto(
            Long id,
            String username,
            String userpassword,
            String name,
            String surname,
            AccountDto account,
            List<SystemTransactionDto> transactions,
            SystemUserGroup userGrop) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
        this.surname = surname;
        this.account = account;
        this.transactions = transactions;
        this.userGroup = userGrop;
    }

   

    public SystemUser asEntity() {
        SystemUser user = new SystemUser();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setUserpassword(this.userpassword);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setAccount(this.account != null ? this.account.asEntity() : null);
        user.setSystemTransactions(SystemTransaction.asEntity(this.transactions != null ? this.transactions : new ArrayList<>()));

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

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public List<SystemTransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<SystemTransactionDto> transactions) {
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

    public SystemUserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(SystemUserGroup userGroup) {
        this.userGroup = userGroup;
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
        return String.valueOf(this.getId());
    }
}
