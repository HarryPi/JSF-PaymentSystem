package dao.SystemTransaction;

import dao.DAO;
import entity.SystemTransaction;

import java.util.List;

public interface SystemTransactionDao extends DAO<SystemTransaction, Long> {
    List<SystemTransaction> getAllTransactionsForUser(Long userId);
}
