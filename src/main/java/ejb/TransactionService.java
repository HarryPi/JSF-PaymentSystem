package ejb;

import entity.SystemTransaction;

import java.util.List;

public interface TransactionService {
    List<SystemTransaction> getAllTransactionsForUser(String username);
}
