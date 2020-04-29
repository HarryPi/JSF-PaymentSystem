package ejb;

import dto.SystemTransactionDto;

import java.util.List;

public interface TransactionService {
    List<SystemTransactionDto> getAllTransactionsForUser(String username);
}
