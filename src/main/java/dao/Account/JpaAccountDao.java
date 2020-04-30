package dao.Account;

import dao.JpaDao;
import entity.Account;

import javax.ejb.Stateless;

@Stateless
public class JpaAccountDao extends JpaDao<Account, Long> implements AccountDao {
    @Override
    public void updateBalance(double newBalance, long id) {
        Account account = this.entityManager.find(Account.class, id);
        account.setBalance(newBalance);
        this.entityManager.merge(account);
    }
}
