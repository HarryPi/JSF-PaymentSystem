package ejb;

import dto.SystemTransactionDto;

import java.util.List;

public interface TransactionService {
    List<SystemTransactionDto> getAllReceivedTransactions(long userId);
    List<SystemTransactionDto> getAllSentTransactions(long userId);
    List<SystemTransactionDto> getPendingRequestedTransactions(long userId);
    int getNoOfPendingRequestedTransactions(long userId);
    void approveTransactionRequest(List<SystemTransactionDto> transactions);
    void rejectTransactionRequest(List<SystemTransactionDto> transactions);
}
