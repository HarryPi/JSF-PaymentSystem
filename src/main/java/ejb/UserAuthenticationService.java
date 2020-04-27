package ejb;

import entity.SystemUser;

public interface UserAuthenticationService {
    void registerUser(SystemUser user);

    void loginUser(String email, String password);
}
