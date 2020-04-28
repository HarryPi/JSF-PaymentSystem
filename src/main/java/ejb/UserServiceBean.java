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
import java.util.List;

@Stateless
public class UserServiceBean implements UserService {

    @PersistenceContext
    EntityManager entityManager;


    /**
     * Registers a new user. A new user will always be acociated with a new account and a group
     * if any of the operations fail the entire transaction will be cancelled
     *
     * @param user             The {@link SystemUser} model to register
     * @param selectedCurrency The Currency type that the user selected see {@link entity.Currency} for available options
     */
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

    /**
     * Gets all the users except the specified user
     * @param selfUsername The user to not return
     * @return A List of users except the user defined as self
     */
    @Override
    public List<SystemUser> getAllUsersExceptSelf(String selfUsername) {
        return entityManager
                .createNamedQuery("getAllUsersExceptSelf", SystemUser.class)
                .setParameter("email", selfUsername)
                .getResultList();
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
