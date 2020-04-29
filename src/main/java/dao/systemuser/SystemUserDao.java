package dao.systemuser;

import dao.DAO;
import entity.SystemUser;

import javax.ejb.Stateless;

public interface SystemUserDao extends DAO<SystemUser, Long> {
    SystemUser getUserByEmail(String email);
}
