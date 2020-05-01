package jsf;

import Exceptions.EmailAlreadyExistsException;
import NavigationConstants.Navigation;
import dto.SystemUserDto;
import ejb.CurrencyService;
import ejb.UserService;
import entity.Currency;
import entity.SystemUser;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;

@Named("authentication")
@ViewScoped
public class AuthBean implements Serializable {

    private String currency;
    private List<Currency> currencies;
    private SystemUserDto userDto;
    private String errorMsg;
    
    @Inject
    private CurrencyService currencyService;

    @Inject
    private LayoutControllerBean layout;

    @Inject
    private Navigation navigation;
    
    @EJB
    UserService userService;

    public AuthBean() {
        this.userDto = new SystemUserDto();
    }

    @PostConstruct
    public void init() {
        currencies = currencyService.getCurrencies();

        // Set default currency to pounds
        currency = Currency.gbPounds;
    }

    public void checkIfAuthenticated() throws IOException {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            String username = context.getExternalContext().getRemoteUser();

            // check if we have a user loaded
            // Redirect
            if (!username.isEmpty()) {
                context.getExternalContext().redirect("/webapps2020/faces/users/transactions.xhtml?faces-redirect=true");
            }

        } catch (Exception e) {
            // Our user is not logged or register thus ignore and leave at login
        }
    }

    public String login() {
        try {
            this.loginToServer(this.userDto.getUsername(), this.userDto.getUserpassword());
        } catch (ServletException e) {
            this.errorMsg = "Invalid username or password";
            return navigation.getLOG_IN_FAILURE();
        }

        if (this.userService.isLoggedUserAdmin()) {
            return navigation.getADMIN_SUCCESS();
        } else {
            return navigation.getLOG_IN_SUCCESS();
        }
    }

    public String logout() {
        try {
            this.userService.logout();
            layout.ensureSidebarHidden();
            return navigation.getLOGOUT();
        } catch (IOException ex) {
            Logger.getLogger(AuthBean.class.getName()).log(Level.SEVERE, null, ex);
            layout.displayFacesMessage("Failed to connect to server!", "Something went wrong please try again", FacesMessage.SEVERITY_ERROR);
            layout.ensureSidebarHidden();
            return navigation.getLOGOUT();
        }
    }

    public String registerAdmin() {
        try {
            String hashedPassword = this.encodePassword(); // Get encoded password

            // Get currency and convert if necessary
            // todo: Convert currency via rest
            SystemUserDto user = this.userDto;
            user.setUserpassword(hashedPassword);

            try {
                userService.registerAdmin(user);
            } catch (EmailAlreadyExistsException e) {
                layout.displayFacesMessage("Failed to register", "Email already exists!", FacesMessage.SEVERITY_ERROR);
                return navigation.getADD_ADMIN_FAILURE();

            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        layout.displayFacesMessage("Sucess", "Admin registered!", FacesMessage.SEVERITY_INFO);
        this.userDto = new SystemUserDto();
        return navigation.getADD_ADMIN_SUCCESS();
    }

    public String register() {
        try {
            String hashedPassword = this.encodePassword(); // Get encoded password

            // Get currency and convert if necessary
            // todo: Convert currency via rest
            SystemUser user = this.userDto.asEntity();
            user.setUserpassword(hashedPassword);

            try {
                userService.registerUser(user, currency);
            } catch (EmailAlreadyExistsException e) {
                layout.displayFacesMessage("Failed to register", "Email already exists!", FacesMessage.SEVERITY_ERROR);
                return navigation.getREGISTER_FAILURE();

            }
            this.loginToServer(this.userDto.getUsername(), this.userDto.getUserpassword());

        } catch (Exception ex) {
            layout.displayFacesMessage(
                    "Failed to login after registertion",
                    "You have been registerd but we where unable to redirect you. Please log in manually",
                    FacesMessage.SEVERITY_ERROR
            );

            return navigation.getREGISTER_FAILURE();

        }

        return navigation.getREGISTER_SUCCESS();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public SystemUserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(SystemUserDto userDto) {
        this.userDto = userDto;
    }

    private String encodePassword() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(this.userDto.getUserpassword().getBytes(StandardCharsets.UTF_8));
        return bytesToHex(md.digest());
    }

    /**
     * Turn bytes into hashed string
     *
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    private void loginToServer(String username, String password) throws ServletException {
        try {
            this.userService.loginUser(username, password);
        } catch (ServletException e) {
            throw e;
        }
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    
}
