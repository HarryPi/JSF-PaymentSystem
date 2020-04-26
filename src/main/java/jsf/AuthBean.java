package jsf;

import ejb.UserAuthenticationService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Named("authentication")
@RequestScoped
public class AuthBean {
    private String email;
    private String password;
    private String name;
    private String lastName;

    @EJB
    UserAuthenticationService store;

    public AuthBean() {
    }


    public String register() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            String hashedPassword = bytesToHex(md.digest());
            store.registerUser(email, hashedPassword, name, lastName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

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

    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
