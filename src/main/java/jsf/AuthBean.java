package jsf;

import ejb.UserAuthenticationService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Named("authentication")
@RequestScoped
public class AuthBean {
    private String username;
    private String userpassword;
    private String name;
    private String lastName;

    @EJB
    UserAuthenticationService store;

    public AuthBean() {
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
        return "/users/user.xhtml";
    }

    public String register() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(userpassword.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = bytesToHex(md.digest());
            store.registerUser(username, hashedPassword, name, lastName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return "/dummy/result";
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

    /**
     * Turn bytes into hashed string
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
