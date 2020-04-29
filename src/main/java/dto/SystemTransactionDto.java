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
    private SystemUserDto transactionOwner;

    /**
     * The other participant of this transaction that either receives or sends money from the transaction owner
     */
    private long transactionParticipantId;

    private SystemUserDto transactionParticipant;

    public SystemTransactionDto() {
    }

    public SystemTransactionDto(@NotNull int amount, @NotNull TransactionStatus status, @NotNull SystemUserDto transactionOwner, @NotNull long transactionParticipantId) {
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipantId = transactionParticipantId;
    }

    public SystemTransaction asEntity() {
        SystemTransaction transaction = new SystemTransaction();
        transaction.setAmount(this.amount);
        transaction.setStatus(this.status);
        transaction.setTransactionOwner(this.transactionOwner.asEntity());
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

    public SystemUserDto getTransactionOwner() {
        return transactionOwner;
    }

    public void setTransactionOwner(SystemUserDto transactionOwner) {
        this.transactionOwner = transactionOwner;
    }

    public long getTransactionParticipantId() {
        return transactionParticipantId;
    }

    public void setTransactionParticipantId(long transactionParticipantId) {
        this.transactionParticipantId = transactionParticipantId;
    }

    public SystemUserDto getTransactionParticipant() {
        return transactionParticipant;
    }

    public void setTransactionParticipant(SystemUserDto transactionParticipant) {
        this.transactionParticipant = transactionParticipant;
    }
}
