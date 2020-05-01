package dto;

import ejb.TransactionStatus;
import entity.SystemTransaction;
import entity.SystemUser;

import javax.validation.constraints.NotNull;

public class SystemTransactionDto {
    private long id;

    private double amount;
    private double preConversionAmount;
    
    private TransactionStatus status;

    /**
     * The account that owns this transaction (i.e the instigator)
     */
    private SystemUser transactionOwner;

    /**
     * The other participant of this transaction that either receives or sends money from the transaction owner
     */
    private long transactionParticipantId;

    private SystemUser transactionParticipant;

    public SystemTransactionDto() {
    }

    public SystemTransactionDto(
            @NotNull double amount,
            @NotNull TransactionStatus status,
            @NotNull SystemUser transactionOwner,
            @NotNull long transactionParticipantId
    ) {
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipantId = transactionParticipantId;
    }

    public SystemTransactionDto(
            long id,
            @NotNull double amount,
            @NotNull TransactionStatus status,
            SystemUser transactionOwner,
            @NotNull long transactionParticipantId,
            @NotNull SystemUser transactionParticipant
    ) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipantId = transactionParticipantId;
        this.transactionParticipant = transactionParticipant;
    }

    public SystemTransactionDto(
            long id,
            double amount,
            TransactionStatus status,
            SystemUser toDto,
            long transactionParticipantId
    ) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.transactionOwner = toDto;
        this.transactionParticipantId = transactionParticipantId;
    }

    public SystemTransaction asEntity() {
        SystemTransaction transaction = new SystemTransaction();
        transaction.setId(this.id);
        transaction.setAmount(this.amount);
        transaction.setStatus(this.status);
        transaction.setTransactionOwner(this.transactionOwner);
        transaction.setTransactionParticipantId(this.transactionParticipantId);
        transaction.setPreConversionAmount(this.preConversionAmount);

        return transaction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
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

    public long getTransactionParticipantId() {
        return transactionParticipantId;
    }

    public void setTransactionParticipantId(long transactionParticipantId) {
        this.transactionParticipantId = transactionParticipantId;
    }

    public SystemUser getTransactionParticipant() {
        return transactionParticipant;
    }

    public void setTransactionParticipant(SystemUser transactionParticipant) {
        this.transactionParticipant = transactionParticipant;
    }

    public double getPreConversionAmount() {
        return preConversionAmount;
    }

    public void setPreConversionAmount(double preConversionAmount) {
        this.preConversionAmount = preConversionAmount;
    }
    
}
