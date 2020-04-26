package jsf;

import ejb.UserAuthenticationService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("authentication")
@RequestScoped
public class AuthBean {
    private String email;
    private String password;

    @EJB
    UserAuthenticationService store;

    public AuthBean() {
    }


    public String register() {
        store.registerUser(email, password);
        return "/dummy/result";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
