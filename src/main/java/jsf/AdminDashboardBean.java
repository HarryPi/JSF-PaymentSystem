/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import NavigationConstants.Navigation;
import java.util.List;
import javax.inject.Named;
import dto.SystemUserDto;
import ejb.UserService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author harry
 */
@Named(value = "dashboard")
@ViewScoped
public class AdminDashboardBean implements Serializable {

    private List<SystemUserDto> allUsers;

    @EJB
    UserService userService;

    @Inject
    Navigation navigation;
    
    /**
     * Creates a new instance of AdminDashboardBean
     */
    public AdminDashboardBean() {
    }

    public double prettifyBalance(double balance) {
        System.err.println(balance);
        double a = Math.floor(balance * 100) / 100;
        System.err.println(a);
        return a;

    }

    public void onLoad() {
        this.allUsers = this.userService.getAllUsers();
    }

    public List<SystemUserDto> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<SystemUserDto> allUsers) {
        this.allUsers = allUsers;
    }

}
