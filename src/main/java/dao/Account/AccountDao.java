package dao.Account;

import dao.DAO;
import entity.Account;

public interface AccountDao extends DAO<Account, Long> {
    void updateBalance(double newBalance, long id);
}
