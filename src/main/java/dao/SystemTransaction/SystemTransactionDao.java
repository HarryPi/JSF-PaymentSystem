package dao.SystemTransaction;

import dao.DAO;
import entity.SystemTransaction;

import java.util.Arrays;
import java.util.List;

public interface SystemTransactionDao extends DAO<SystemTransaction, Long> {
    List<SystemTransaction> getAllSentTransactions(Long userId);
    List<SystemTransaction> getAllReceivedTransactions(Long userId);
    List<SystemTransaction> getAllReceivedRequests(long userId);
}
