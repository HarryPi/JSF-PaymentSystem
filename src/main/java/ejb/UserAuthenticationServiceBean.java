package ejb;

import entity.SystemUser;
import entity.SystemUserGroup;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserAuthenticationServiceBean implements UserAuthenticationService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void registerUser(String email, String password, String name, String lastName) {
        SystemUser user = new SystemUser(email, password, name, lastName);
        SystemUserGroup userGroup = new SystemUserGroup(email, "users");

        entityManager.persist(user);
        entityManager.persist(userGroup);
    }

    /**
     *
     * @param email
     * @param password
     */
    @Override
    public void loginUser(String email, String password) {

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CommentStore: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("CommentStore: PreDestroy");
    }
}
