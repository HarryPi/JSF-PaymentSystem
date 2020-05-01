package ejb;

import Exceptions.EmailAlreadyExistsException;
import dao.SystemUserGroup.SystemUserGroupDao;
import dao.systemuser.SystemUserDao;
import dto.SystemUserDto;
import entity.Account;
import entity.Currency;
import entity.SystemUser;
import entity.SystemUserGroup;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

/**
 * 
 * @author harry
 */
@Stateless
@RolesAllowed({"users", "admins"})
public class UserServiceBean implements UserService {

    @EJB(name = "SystemUserDao")
    private SystemUserDao userDao;

    @EJB
    private SystemUserGroupDao userGroupDao;

    @Inject
    private CurrencyService currencyService;

    public SystemUserDto getCurrentUser() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        return userDao.getUserByEmail(username).get().toDto();
    }

    /**
     * Registers a new user. A new user will always be asosciated with a new
     * account and a group if any of the operations fail the entire transaction
     * will be cancelled
     *
     * @param user The {@link SystemUser} model to register
     * @param selectedCurrency The Currency type that the user selected see
     * {@link entity.Currency} for available options
     */
    @Override
    @PermitAll
    @Transactional(Transactional.TxType.REQUIRED)
    public void registerUser(SystemUser user, String selectedCurrency) throws EmailAlreadyExistsException {
        
        // Check if email already exists in db
        SystemUser userInDb = userDao.getUserByEmail(user.getUsername()).orElse(null);
        
        if (userInDb != null) {
            throw new EmailAlreadyExistsException("This email already exists");
        }
        
        SystemUserGroup userGroup = new SystemUserGroup(user, user.getUsername(), "users");

        double balance = 1000.0;
        if (!selectedCurrency.equals(Currency.gbPounds)) {
            // Convert to currency
            balance = this.currencyService
                    .convertToCurrency(Currency.gbPounds, selectedCurrency, 1000);
        }

        Account account = new Account(balance, selectedCurrency, user);
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
        //todo: remember to transfer login code here
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

    @Override
    public SystemUserDto getByEmail(String email) {
        return this.userDao.getUserByEmail(email).get().toDto();
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CommentStore: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("CommentStore: PreDestroy");
    }

    @Override
    @RolesAllowed("admins")
    @Transactional(Transactional.TxType.REQUIRED)
    public void registerAdmin(SystemUserDto user) {
        SystemUserGroup userGroup = new SystemUserGroup(user.asEntity(), user.getUsername(), "admins");

        userDao.persist(user.asEntity()); // No need to persist account as it is already attached to user
        userGroupDao.persist(userGroup);
    }

    @Override
    public boolean isLoggedUserAdmin() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        return context.isUserInRole("admins");
    }

    @Override
    public List<SystemUserDto> getAllSimpleUsers() {
        return SystemUser.toDto(userDao.getAllSimpleUsers());
    }

    @Override
    public List<SystemUserDto> getAllAdminUsers() {
        return SystemUser.toDto(userDao.getAllAdminUsers());
    }
}
