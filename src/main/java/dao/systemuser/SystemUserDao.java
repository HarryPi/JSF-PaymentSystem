package dao.systemuser;

import dao.DAO;
import entity.SystemUser;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;

public interface SystemUserDao extends DAO<SystemUser, Long> {
    Optional<SystemUser> getUserByEmail(String email);
    List<SystemUser> getAllSimpleUsers();
    List<SystemUser> getAllAdminUsers();
}
