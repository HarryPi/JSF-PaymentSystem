package ejb;

import dao.SystemTransaction.SystemTransactionDao;
import dao.systemuser.SystemUserDao;
import entity.SystemTransaction;
import entity.SystemUser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class TransactionServiceBean implements TransactionService {

    @EJB
    SystemTransactionDao transactionDao;

    @EJB
    SystemUserDao userDao;

    public TransactionServiceBean() {
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<SystemTransaction> getAllTransactionsForUser(String username) {
        SystemUser user = userDao.getUserByEmail(username);
        // This could have done by email but assuming transactions are an insane ammount
        // It would be faster to do this query by the ID which is index and well worth the double query
        List<SystemTransaction> systemTransactions = transactionDao.getAllTransactionsForUser(user.getId());

        return systemTransactions;
    }

}
