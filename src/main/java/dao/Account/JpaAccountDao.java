package dao.Account;

import dao.JpaDao;
import entity.Account;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class JpaAccountDao extends JpaDao<Account, Long> implements AccountDao {
    @Override
    public void updateBalance(int newBalance, long id) {
        Account account = this.entityManager.find(Account.class, id);
        account.setBalance(newBalance);
        this.entityManager.merge(account);
    }
}
