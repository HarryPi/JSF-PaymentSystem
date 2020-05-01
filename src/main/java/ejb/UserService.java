package ejb;

import Exceptions.EmailAlreadyExistsException;
import dto.SystemUserDto;
import entity.SystemUser;
import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;

public interface UserService {

    /**
     * Registers an administrator
     *
     * @param user Admin to add {@link SystemUserDto}
     * @throws Exceptions.EmailAlreadyExistsException
     */
    void registerAdmin(SystemUserDto user) throws EmailAlreadyExistsException;;

    /**
     * Registers a user as simple user
     *
     * @param user
     * @param selectedCurrency
     * @throws EmailAlreadyExistsException
     */
    void registerUser(SystemUser user, String selectedCurrency) throws EmailAlreadyExistsException;

    /**
     * Logs a user in
     *
     * @param email
     * @param password
     * @throws javax.servlet.ServletException
     */
    void loginUser(String email, String password) throws ServletException;

    SystemUserDto getCurrentUser();

    /**
     * Gets all users admins or users
     *
     * @return
     */
    List<SystemUserDto> getAllUsers();

    SystemUserDto findUser(long id);

    SystemUserDto getByEmail(String email);

    /**
     * Checks if the logged user is an admin
     *
     * @return
     */
    boolean isLoggedUserAdmin();

    /**
     * Gets the simple users
     *
     * @return
     */
    List<SystemUserDto> getAllSimpleUsers();

    /**
     * Gets only the admin users
     *
     * @return
     */
    List<SystemUserDto> getAllAdminUsers();
    
    /**
     *  Logs out the current user
     * @throws java.io.IOException
     */
    void logout() throws IOException;

}
