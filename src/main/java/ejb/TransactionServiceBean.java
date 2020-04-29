package ejb;

import dao.SystemTransaction.SystemTransactionDao;
import dao.systemuser.SystemUserDao;
import dto.SystemTransactionDto;
import dto.SystemUserDto;
import entity.SystemTransaction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    public List<SystemTransactionDto> getAllTransactionsForUser(String username) {
        SystemUserDto user = userDao.getUserByEmail(username).toDto();
        // This could have done by email but assuming transactions are an insane ammount
        // It would be faster to do this query by the ID which is index and well worth the double query
        List<SystemTransactionDto> systemTransactions = SystemTransaction.asDto(transactionDao.getAllTransactionsForUser(user.getId()));

        return systemTransactions;
    }

}
