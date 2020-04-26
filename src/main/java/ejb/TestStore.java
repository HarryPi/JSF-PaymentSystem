package ejb;

import entity.User;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class TestStore {
    List<User> users;

    public TestStore() {
        this.users = new ArrayList<>();
    }

    public synchronized List<User> getUserList() {
        return this.users;
    }

    public synchronized void addUser(String name, String password) {
        User auth = new User(name, password);
        users.add(auth);
        System.out.println(auth.getEmail());
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
