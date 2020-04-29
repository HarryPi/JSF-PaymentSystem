package dao.SystemTransaction;

import dao.JpaDao;
import entity.SystemTransaction;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class JpaSystemTransactionDao extends JpaDao<SystemTransaction, Long> implements SystemTransactionDao {
    @Override
    public List<SystemTransaction> getAllTransactionsForUser(Long userId) {
        return this.entityManager
                .createQuery(
                        "select t from SystemTransaction t " +
                        "where t.transactionOwner.id = :user",
                        SystemTransaction.class)
                .setParameter("user", userId)
                .getResultList();
    }
}
