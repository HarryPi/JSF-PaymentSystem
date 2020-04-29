package dto;

import ejb.TransactionStatus;
import entity.SystemTransaction;
import entity.SystemUser;

import javax.validation.constraints.NotNull;

public class SystemTransactionDto {
    private String id;

    private int amount;

    private TransactionStatus status;

    /**
     * The account that owns this transaction (i.e the instigator)
     */
    private SystemUser transactionOwner;

    /**
     * The other participant of this transaction that either receives or sends money from the transaction owner
     */
    private String transactionParticipantId;

    public SystemTransactionDto() {
    }

    public SystemTransactionDto(@NotNull int amount, @NotNull TransactionStatus status, @NotNull SystemUser transactionOwner, @NotNull String transactionParticipantId) {
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipantId = transactionParticipantId;
    }

    public SystemTransaction asEntity() {
        SystemTransaction transaction = new SystemTransaction();
        transaction.setAmount(this.amount);
        transaction.setStatus(this.status);
        transaction.setTransactionOwner(this.transactionOwner);
        transaction.setTransactionParticipantId(this.transactionParticipantId);

        return transaction;
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
