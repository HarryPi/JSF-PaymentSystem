package ejb;

import dao.Account.AccountDao;
import dao.SystemTransaction.SystemTransactionDao;
import dao.systemuser.SystemUserDao;
import entity.Account;
import entity.SystemTransaction;
import entity.SystemUser;
import javax.annotation.security.RolesAllowed;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@RolesAllowed({"users", "admins"})
public class PaymentServiceBean implements PaymentService {

    @EJB
    SystemUserDao userDao;

    @EJB
    AccountDao accountDao;

    @EJB
    SystemTransactionDao transactionDao;

    @Inject
    CurrencyService currencyService;

    public PaymentServiceBean() {
    }

    /**
     * Makes a payment from a user to another users {@link entity.Account}
     *
     * @param from User email to take money from
     * @param to User email to add money to
     * @param amount The amount
     * @return Returns if this action is possible to complete or not
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean pay(String from, String to, double amount) {
        SystemUser userFrom = userDao.getUserByEmail(from).get();
        SystemUser userTo = userDao.getUserByEmail(to).get();

        boolean canTransferComplete = userFrom.getAccount().getBalance() - amount >= 0;

        if (!canTransferComplete) {
            return false;
        }
        Account accountFrom = userFrom.getAccount();
        Account accountTo = userTo.getAccount();

        accountDao.updateBalance(accountFrom.getBalance() - amount, accountFrom.getId());

        // Before adding amount convert to currency
        double convertedAmount = this.currencyService.convertToCurrency(accountFrom.getCurrency(), accountTo.getCurrency(), amount);
        accountDao.updateBalance(accountTo.getBalance() + convertedAmount, accountTo.getId());

        // Create Transaction to reflect payment
        transactionDao.persist(new SystemTransaction(convertedAmount, TransactionStatus.SENT, userFrom, userTo.getId(), amount));
        return true;
    }

    @Override
    public void requestMoney(String from, String to, double amount) {
        SystemUser userTo = userDao.getUserByEmail(to).get();
        SystemUser userFrom = userDao.getUserByEmail(from).get();

        double convertAmount = this.currencyService.convertToCurrency(userFrom.getAccount().getCurrency(), userTo.getAccount().getCurrency(), amount);
        // Create Transaction to reflect payment
        transactionDao.persist(new SystemTransaction(convertAmount, TransactionStatus.REQUEST, userFrom, userTo.getId(), amount));
    }
}
