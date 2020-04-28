package ejb;

import entity.Account;
import entity.SystemUser;
import entity.SystemUserGroup;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class UserAuthenticationServiceBean implements UserAuthenticationService {

    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void registerUser(SystemUser user, String selectedCurrency) {
        SystemUserGroup userGroup = new SystemUserGroup(user.getUsername(), "users");
        Account account = new Account(1000, selectedCurrency, user);
        user.setAccount(account);
        System.out.println("Registering...");

        entityManager.persist(user);
        entityManager.persist(userGroup);
        entityManager.persist(account);

    }

    /**
     * @param email
     * @param password
     */
    @Override
    public void loginUser(String email, String password) {

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CommentStore: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("CommentStore: PreDestroy");
    }
}
