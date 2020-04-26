package ejb;

public interface UserAuthenticationService {
    void registerUser(String email, String password, String name, String lastName);

    void loginUser(String email, String password);
}
