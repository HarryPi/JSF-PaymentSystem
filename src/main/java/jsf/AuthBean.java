package jsf;

import dto.SystemUserDto;
import ejb.CurrencyService;
import ejb.UserService;
import entity.Currency;
import entity.SystemUser;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
    private String currency;
    private List<Currency> currencies;
    private SystemUserDto userDto;

    @Inject
    private CurrencyService currencyService;

    @EJB
    UserService store;

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
            System.out.println("Success!");
        } catch (ServletException e) {
            System.out.println("Error :(");
            System.out.println(String.format("Error %s", e.toString()));
            return "/index.xhtml";
        }
        return "/users/transactions.xhtml?faces-redirect=true";
    }

    public String register() {
        try {
            String hashedPassword = this.encodePassword(); // Get encoded password

            // Get currency and convert if necessary
            // todo: Convert currency via rest
            SystemUser user = this.userDto.asEntity();
            user.setUserpassword(hashedPassword);

            store.registerUser(user, currency);
            this.loginToServer(this.userDto.getUsername(), this.userDto.getUserpassword());

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return "/users/transactions.xhtml?faces-redirect=true";
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
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    private void loginToServer(String username, String password) throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.login(username, password);
    }
}
