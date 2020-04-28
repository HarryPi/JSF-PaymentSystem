package jsf;

import ejb.CurrencyService;
import ejb.UserAuthenticationService;
import entity.Account;
import entity.Currency;
import entity.SystemUser;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Named("authentication")
@SessionScoped
public class AuthBean implements Serializable {
    private String username;
    private String userpassword;
    private String name;
    private String lastName;
    private String currency;

    private List<Currency> currencies;

    @Inject
    private CurrencyService currencyService;

    @EJB
    UserAuthenticationService store;

    public AuthBean() {
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
                context.getExternalContext().redirect("/webapps2020/faces/users/user.xhtml?faces-redirect=true");
            }

        } catch (Exception e) {
            // Our user is not logged or register thus ignore and leave at index
        }
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("Username: " + username);
        System.out.println("Password: " + userpassword);
        try {
            //this method will actually check in the realm you configured in the web.xml file for the provided credentials
            request.login(this.username, this.userpassword);
            System.out.println("Success!");
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed:" + e));
            System.out.println("Error :(");
            System.out.println(String.format("Error %s", e.toString()));
            return "/index.xhtml";
        }
        System.out.println(request.getRequestURI());
        return "/users/user.xhtml?faces-redirect=true";
    }

    public String register() {
        try {
            String hashedPassword = this.encodePassword(); // Get encoded password

            // Get currency and convert if necessary
            // todo: Convert currency
            SystemUser user = new SystemUser(username, hashedPassword, name, lastName, null);
            store.registerUser(user, currency);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return "/users/user.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    private String encodePassword() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(userpassword.getBytes(StandardCharsets.UTF_8));
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
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
