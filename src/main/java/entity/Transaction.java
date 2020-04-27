package entity;


import ejb.TransactionStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    private int amount;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus status;

    @NotNull
    @ManyToOne
    /**
     * The account that owns this transaction (i.e the instigator)
     */
    private Account transactionOwner;

    /**
     * The other participant of this transaction that either receives or sends money from the transaction owner
     */
    @NotNull
    @ManyToOne
    private Account transactionParticipant;

    public Transaction() {
    }

    public Transaction(
            @NotNull int amount,
            @NotNull TransactionStatus status,
            @NotNull Account transactionOwner,
            @NotNull Account transactionParticipant
    ) {
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipant = transactionParticipant;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Account getTransactionOwner() {
        return transactionOwner;
    }

    public void setTransactionOwner(Account transactionOwner) {
        this.transactionOwner = transactionOwner;
    }

    public Account getTransactionParticipant() {
        return transactionParticipant;
    }

    public void setTransactionParticipant(Account transactionParticipant) {
        this.transactionParticipant = transactionParticipant;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
