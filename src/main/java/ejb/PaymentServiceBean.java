package ejb;

import dao.Account.AccountDao;
import dao.SystemTransaction.SystemTransactionDao;
import dao.systemuser.SystemUserDao;
import entity.Account;
import entity.SystemTransaction;
import entity.SystemUser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class PaymentServiceBean implements PaymentService {

    @EJB
    SystemUserDao userDao;

    @EJB
    AccountDao accountDao;

    @EJB
    SystemTransactionDao transactionDao;

    public PaymentServiceBean() {
    }

    /**
     * Makes a payment from a user to another users {@link entity.Account}
     * @param from User email to take money from
     * @param to User email to add money to
     * @param amount The amount
     * @return Returns if this action is possible to complete or not
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean pay(String from, String to, int amount) {
        SystemUser userFrom = userDao.getUserByEmail(from);
        SystemUser userTo = userDao.getUserByEmail(to);

        boolean canTransferComplete = userFrom.getAccount().getBalance() - amount >= 0;

        if (!canTransferComplete) {
            return false;
        }
        Account accountFrom = userFrom.getAccount();
        Account accountTo = userTo.getAccount();

        accountDao.updateBalance(accountFrom.getBalance() - amount, accountFrom.getId());
        accountDao.updateBalance(accountTo.getBalance() + amount, accountTo.getId());

        // Create Transaction to reflect payment
        transactionDao.persist(new SystemTransaction(amount, TransactionStatus.SENT, userFrom, userTo.getId()));
        transactionDao.persist(new SystemTransaction(amount, TransactionStatus.RECEIVED, userTo, userFrom.getId()));
        return true;
    }

    @Override
    public void requestMoney(String from, String to, int amount) {
        SystemUser userFrom = userDao.getUserByEmail(from);
        SystemUser userTo = userDao.getUserByEmail(to);

        // Create Transaction to reflect payment
        transactionDao.persist(new SystemTransaction(amount, TransactionStatus.REQUEST_SENT, userFrom, userTo.getId()));
        transactionDao.persist(new SystemTransaction(amount, TransactionStatus.REQUEST_RECEIVED, userTo, userFrom.getId()));
    }
}
