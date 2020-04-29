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
    private long transactionParticipantId;

    public SystemTransaction() {
    }

    public SystemTransaction(@NotNull int amount, @NotNull TransactionStatus status, @NotNull SystemUser transactionOwner, @NotNull long transactionParticipantId) {
        this.amount = amount;
        this.status = status;
        this.transactionOwner = transactionOwner;
        this.transactionParticipantId = transactionParticipantId;
    }

    public SystemTransactionDto asDto() {
        return new SystemTransactionDto(
                this.amount,
                this.status,
                this.transactionOwner.toDto(),
                this.transactionParticipantId
        );
    }

    public static List<SystemTransactionDto> asDto(List<SystemTransaction> transactions) {
        List<SystemTransactionDto> dtos = new ArrayList<>();

        for (SystemTransaction transaction : transactions) {
            dtos.add(transaction.asDto());
        }

        return dtos;
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

    public long getTransactionParticipantId() {
        return transactionParticipantId;
    }

    public void setTransactionParticipantId(long transactionParticipantId) {
        this.transactionParticipantId = transactionParticipantId;
    }
}
