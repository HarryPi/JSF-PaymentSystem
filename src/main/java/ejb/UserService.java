package ejb;

import dto.SystemUserDto;
import entity.SystemUser;

import java.util.List;

public interface UserService {
    /**
     * Registers an administrator
     * @param user Admin to add {@link SystemUserDto}
     */
    void registerAdmin(SystemUserDto user);
    /**
     * Registers a user as simple user
     * @param user
     * @param selectedCurrency 
     */
    void registerUser(SystemUser user, String selectedCurrency);
    /**
     * Logs a user in
     * @param email
     * @param password 
     */
    void loginUser(String email, String password);
    SystemUserDto getCurrentUser();
    List<SystemUserDto> getAllUsers();
    SystemUserDto findUser(long id);
    SystemUserDto getByEmail(String email);
    
    
}
