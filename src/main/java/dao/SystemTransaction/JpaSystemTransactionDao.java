package dao.SystemTransaction;

import dao.JpaDao;
import ejb.TransactionStatus;
import entity.SystemTransaction;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class JpaSystemTransactionDao extends JpaDao<SystemTransaction, Long> implements SystemTransactionDao {
    @Override
    public List<SystemTransaction> getAllSentTransactions(Long userId) {
        return this.entityManager
                .createQuery(
                        "select t from SystemTransaction t " +
                        "where t.transactionOwner.id = :user " +
                                "and t.status <> :request",
                        SystemTransaction.class)
                .setParameter("user", userId)
                .setParameter("request", TransactionStatus.REQUEST)
                .getResultList();
    }

    @Override
    public List<SystemTransaction> getAllReceivedTransactions(Long userId) {
        return this.entityManager
                .createQuery(
                        "select t from SystemTransaction t " +
                                "where t.transactionParticipantId = :user " +
                                "and t.status <> :request",
                        SystemTransaction.class)
                .setParameter("user", userId)
                .setParameter("request", TransactionStatus.REQUEST)
                .getResultList();
    }

    @Override
    public List<SystemTransaction> getAllReceivedRequests(long userId) {
        return this.entityManager
                .createQuery(
                        "select t from SystemTransaction t " +
                                "where t.transactionParticipantId = :user " +
                                "and t.status = :request",
                        SystemTransaction.class)
                .setParameter("user", userId)
                .setParameter("request", TransactionStatus.REQUEST)
                .getResultList();

    }
}
