package entity;

import dto.SystemTransactionDto;
import ejb.TransactionStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "getAllTransactionsWithUserId",
        query = "select t from SystemTransaction t where t.transactionOwner.id = :userId"
)
@Entity
public class SystemTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private double amount;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus status;

    /**
     * The account that owns this transaction (i.e the instigator)
     */
    @NotNull
    @ManyToOne
    private SystemUser transactionOwner;

    /**
     * The other participant of this transaction that either receives or sends
     * money from the transaction owner
     */
    @NotNull
    private long transactionParticipantId;

    private double preConversionAmount;

    public SystemTransaction() {
    }

    public SystemTransaction(
            @NotNull long id,
            @NotNull double amount,
            @NotNull TransactionStatus status,
            @NotNull SystemUser transactionOwner,
            @NotNull long transactionParticipantId) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipantId = transactionParticipantId;
    }

    public SystemTransaction(
            @NotNull double amount,
            @NotNull TransactionStatus status,
            @NotNull SystemUser transactionOwner,
            @NotNull long transactionParticipantId,
            double preConversionAmount
    ) {
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipantId = transactionParticipantId;
        this.preConversionAmount = preConversionAmount;
    }

    public SystemTransactionDto asDto() {
        SystemTransactionDto t = new SystemTransactionDto(
                this.id,
                this.amount,
                this.status,
                this.transactionOwner.toDto(),
                this.transactionParticipantId
        );
        t.setPreConversionAmount(this.preConversionAmount);
        return t;
    }

    public static List<SystemTransactionDto> asDto(List<SystemTransaction> transactions) {
        List<SystemTransactionDto> dtos = new ArrayList<>();

        for (SystemTransaction transaction : transactions) {
            dtos.add(transaction.asDto());
        }

        return dtos;
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

    public void setAmount(double amount) {
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

    public double getPreConversionAmount() {
        return preConversionAmount;
    }

    public void setPreConversionAmount(double preConversionAmount) {
        this.preConversionAmount = preConversionAmount;
    }

}
