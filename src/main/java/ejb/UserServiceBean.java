package ejb;

import dao.SystemUserGroup.SystemUserGroupDao;
import dao.systemuser.SystemUserDao;
import dto.SystemUserDto;
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

    @EJB
    private SystemUserGroupDao userGroupDao;

    @PersistenceContext
    EntityManager entityManager;


    public SystemUserDto getCurrentUser() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        return userDao.getUserByEmail(username).toDto();
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

        userDao.persist(user); // No need to persist account as it is already attached to user
        userGroupDao.persist(userGroup);
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
     * @return A List of {@link SystemUserDto}
     */
    @Override
    public List<SystemUserDto> getAllUsers() {
       return SystemUser.toDto(userDao.getAll());
    }

    @Override
    public SystemUserDto findUser(long id) {
        return this.userDao.findById(id).toDto();
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
