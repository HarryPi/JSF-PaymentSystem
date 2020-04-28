package jsf;

import ejb.CurrencyService;
import ejb.UserService;
import entity.SystemUser;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "transfer")
@SessionScoped
public class TransferMoneyBean implements Serializable {

    private List<SystemUser> users;
    private SystemUser currentUser;
    private String usersPreferredCurrency;


    @EJB
    UserService userService;

    @Inject
    CurrencyService currencyService;

    @Inject
    LayoutControllerBean layout;

    public TransferMoneyBean() {
    }

    public void commitTransfer() {
        System.out.println("Hello!");
    }

    public String getSymbolForUsersPreferredCurrency() {
        return this.currencyService.get(currentUser.getAccount().getCurrency()).getDisplaySymbol();
    }

    /**
     * Loads all users into users but removes the self user
     * Then assignes the self user into currentUser
     */
    public void loadAllUsers() {
        System.out.println("loading all users...");

        // Get all users
        users = new ArrayList<>(userService.getAllUsers());

        // Get current users email
        String currentUsersEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        // users will be displayed to current user for transfer options thus the current user should not be displayed
        currentUser = users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(currentUsersEmail))
                .findFirst()
                .orElse(null);

        users.removeIf(user -> user.getUsername().equalsIgnoreCase(currentUsersEmail));

        // Set loading as false and will update all components via ajax
        layout.setLoading(false);
        usersPreferredCurrency = getSymbolForUsersPreferredCurrency();
    }

    public SystemUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(SystemUser currentUser) {
        this.currentUser = currentUser;
    }

    public List<SystemUser> getUsers() {
        return users;
    }

    public void setUsers(List<SystemUser> users) {
        this.users = users;
    }

    public String getUsersPreferredCurrency() {
        return usersPreferredCurrency;
    }

    public void setUsersPreferredCurrency(String usersPreferredCurrency) {
        this.usersPreferredCurrency = usersPreferredCurrency;
    }


}
