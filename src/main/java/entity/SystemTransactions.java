package entity;


import ejb.TransactionStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NamedQuery(
        name = "getAllTransactionsWithUserId",
        query = "select t from SystemTransactions t where t.transactionOwner.id = :userId"
)
@Entity
public class SystemTransactions implements Serializable {

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
    private SystemUser transactionOwner;

    /**
     * The other participant of this transaction that either receives or sends money from the transaction owner
     */
    @NotNull
    private String transactionParticipantId;

    public SystemTransactions() {
    }

    public SystemTransactions(@NotNull int amount, @NotNull TransactionStatus status, @NotNull SystemUser transactionOwner, @NotNull String transactionParticipantId) {
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipantId = transactionParticipantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public SystemUser getTransactionOwner() {
        return transactionOwner;
    }

    public void setTransactionOwner(SystemUser transactionOwner) {
        this.transactionOwner = transactionOwner;
    }

    public String getTransactionParticipantId() {
        return transactionParticipantId;
    }

    public void setTransactionParticipantId(String transactionParticipantId) {
        this.transactionParticipantId = transactionParticipantId;
    }
}
