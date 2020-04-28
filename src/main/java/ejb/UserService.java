package ejb;

import entity.SystemUser;

import java.util.List;

public interface UserService {
    void registerUser(SystemUser user, String selectedCurrency);
    void loginUser(String email, String password);
    List<SystemUser> getAllUsersExceptSelf(String selfUsername);
}
