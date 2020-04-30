package ejb;

import dto.SystemUserDto;
import entity.SystemUser;

import java.util.List;

public interface UserService {
    void registerUser(SystemUser user, String selectedCurrency);
    void loginUser(String email, String password);
    SystemUserDto getCurrentUser();
    List<SystemUserDto> getAllUsers();
    SystemUserDto findUser(long id);
    SystemUserDto getByEmail(String email);

}
