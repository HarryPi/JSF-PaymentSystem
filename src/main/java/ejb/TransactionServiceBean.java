package ejb;

import entity.SystemTransactions;
import entity.SystemUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class TransactionServiceBean implements TransactionService {

    @PersistenceContext
    private EntityManager entityManager;

    public TransactionServiceBean() {
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<SystemTransactions> getAllTransactionsForUser(String username) {
        SystemUser user = entityManager
                .createNamedQuery("getUserByUsername", SystemUser.class)
                .setParameter("email", username)
                .getSingleResult();

        // This could have done by email but assuming transactions are an insane ammount
        // It would be faster to do this query by the ID which is index and well worth the double query
        List<SystemTransactions> systemTransactions = entityManager
                .createNamedQuery("getAllTransactionsWithUserId", SystemTransactions.class)
                .setParameter("userId", user.getId())
                .getResultList();

        return systemTransactions;
    }

}
