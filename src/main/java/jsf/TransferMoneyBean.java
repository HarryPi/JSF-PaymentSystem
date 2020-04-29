package jsf;

import dto.SystemUserDto;
import ejb.CurrencyService;
import ejb.PaymentService;
import ejb.UserService;
import entity.SystemUser;

import javax.ejb.EJB;
import javax.el.MethodExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "transfer")
@SessionScoped
public class TransferMoneyBean implements Serializable {

    private SystemUserDto selectedUser;
    private List<SystemUserDto> users;
    private SystemUserDto currentUser;
    private String usersPreferredCurrency;
    private int amount;

    @EJB
    UserService userService;

    @EJB
    PaymentService paymentService;

    @Inject
    CurrencyService currencyService;

    @Inject
    LayoutControllerBean layout;

    public TransferMoneyBean() {
    }

    public void requestMoney() {
        paymentService.requestMoney(currentUser.getUsername(), selectedUser.getUsername(), amount);
        this.displayFacesMessage("Success", "Request sent successfully", FacesMessage.SEVERITY_INFO);
    }

    public void commitTransfer() {

        if (paymentService.pay(currentUser.getUsername(), selectedUser.getUsername(), amount)) {
            this.displayFacesMessage("Success", "Transaction completed successfully", FacesMessage.SEVERITY_INFO);
        } else {
            this.displayFacesMessage("Failure", "Transaction failed to complete!", FacesMessage.SEVERITY_ERROR);
        }
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

        selectedUser = users.stream().findFirst().orElse(null);
        usersPreferredCurrency = getSymbolForUsersPreferredCurrency();

        layout.setLoading(false); // Stop indicator
    }

    private void displayFacesMessage(String title, String description, FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, title, description));
    }

    public SystemUserDto getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(SystemUserDto currentUser) {
        this.currentUser = currentUser;
    }

    public List<SystemUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<SystemUserDto> users) {
        this.users = users;
    }

    public String getUsersPreferredCurrency() {
        return usersPreferredCurrency;
    }

    public void setUsersPreferredCurrency(String usersPreferredCurrency) {
        this.usersPreferredCurrency = usersPreferredCurrency;
    }

    public void setSelectedUser(SystemUserDto dto) {
        this.selectedUser = dto;
    }

    public SystemUserDto getSelectedUser() {
        return selectedUser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UserService getUserService() {
        return this.userService;
    }


}
