import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named("authentication")
@RequestScoped
public class Authentication {
    private String email;
    private String password;

    @EJB
    TestStore store;

    public Authentication() {
    }


    public String submit() {
        store.addUser(email, password);
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

    public List<User> getUserList() { return store.getUserList(); }
}
