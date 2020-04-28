package ejb;

import entity.SystemTransactions;

import java.util.List;

public interface TransactionService {
    List<SystemTransactions> getAllTransactionsForUser(String username);
}
