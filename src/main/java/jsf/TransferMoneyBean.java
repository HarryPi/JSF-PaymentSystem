package jsf;

import ejb.UserService;
import entity.SystemUser;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "transfer")
@RequestScoped
public class TransferMoneyBean {

    private List<SystemUser> users;

    @EJB
    UserService userService;

    @Inject
    LayoutControllerBean layout;

    public TransferMoneyBean() {
    }

    public void loadAllUsersExceptSelf() {
        System.out.println("loading all users...");
        String selfUsername = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        users = userService.getAllUsersExceptSelf(selfUsername);
        System.out.println(users.size());
        layout.setLoading(false);
    }

    public List<SystemUser> getUsers() {
        return users;
    }

    public void setUsers(List<SystemUser> users) {
        this.users = users;
    }

}
