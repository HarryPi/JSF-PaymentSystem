/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.util.List;
import javax.inject.Named;
import dto.SystemUserDto;
import ejb.CurrencyService;
import ejb.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;


/**
 *
 * @author harry
 */
@Named(value = "dashboard")
@ViewScoped
public class AdminDashboardBean implements Serializable{

    private List<SystemUserDto> allUsers; 
    
    @EJB
    UserService userService;
    
 
    /**
     * Creates a new instance of AdminDashboardBean
     */
    public AdminDashboardBean() {
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
