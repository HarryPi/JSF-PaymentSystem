package ejb;

import entity.SystemUser;

public interface UserAuthenticationService {
    void registerUser(SystemUser user, String selectedCurrency);

    void loginUser(String email, String password);
}
