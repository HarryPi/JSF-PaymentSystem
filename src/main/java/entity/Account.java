package entity;

import com.sun.istack.internal.Nullable;

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
    @OneToOne
    private SystemUser systemUser;

    @NotNull
    @OneToMany
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(@NotNull int balance, @NotNull SystemUser systemUser, @NotNull List<Transaction> transactions) {
        this.balance = balance;
        this.systemUser = systemUser;
        this.transactions = transactions;
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

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
