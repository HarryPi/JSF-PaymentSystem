package dao.systemuser;

import dao.JpaDao;
import entity.SystemUser;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class JpaSystemUserDao extends JpaDao<SystemUser, Long> implements SystemUserDao {
    @Override
    public SystemUser getUserByEmail(String email) {
        Query query = this.entityManager.createQuery(
                "select u from SystemUser u where u.username = :email",
                SystemUser.class
        );
        query.setParameter("email", email);

        return (SystemUser) query.getSingleResult();
    }
}
