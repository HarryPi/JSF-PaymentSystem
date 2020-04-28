package ejb;

import dao.systemuser.SystemUserDao;
import entity.Account;
import entity.SystemUser;
import entity.SystemUserGroup;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class UserServiceBean implements UserService {

    @EJB(name = "SystemUserDao")
    private SystemUserDao userDao;

    @PersistenceContext
    EntityManager entityManager;


    public SystemUser getCurrentUser() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        return userDao.getUserByEmail(username);
    }

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

        userDao.persist(user);
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
     * Gets all the registered users
     *
     * @return A List of {@link SystemUser}
     */
    @Override
    public List<SystemUser> getAllUsers() {
       return userDao.getAll();
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
