package ejb;

import entity.Account;
import entity.SystemUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class PaymentServiceBean implements PaymentService {

    @PersistenceContext
    EntityManager entityManager;

    public PaymentServiceBean() {
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void pay(String from, String to, int amount) {
        SystemUser userFrom = entityManager.createNamedQuery("getUserByUsername", SystemUser.class)
                .setParameter("email", from)
                .getSingleResult();

        userFrom.getAccount().setBalance(userFrom.getAccount().getBalance() - amount);

        SystemUser userTo = entityManager.createNamedQuery("getUserByUsername", SystemUser.class)
                .setParameter("email", to)
                .getSingleResult();

        userTo.getAccount().setBalance(userTo.getAccount().getBalance() - amount);

    }
}
