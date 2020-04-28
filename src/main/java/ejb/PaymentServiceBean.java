package ejb;

import dao.systemuser.SystemUserDao;
import entity.Account;
import entity.SystemUser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class PaymentServiceBean implements PaymentService {

    @EJB
    SystemUserDao userDao;

    public PaymentServiceBean() {
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void pay(String from, String to, int amount) {
        SystemUser userFrom = userDao.getUserByEmail(from);

        userFrom.getAccount().setBalance(userFrom.getAccount().getBalance() - amount);

        SystemUser userTo = userDao.getUserByEmail(to);

        userTo.getAccount().setBalance(userTo.getAccount().getBalance() - amount);

    }
}
