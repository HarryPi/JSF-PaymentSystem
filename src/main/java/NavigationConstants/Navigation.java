/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NavigationConstants;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author harry
 */
@Named("nav")
@ApplicationScoped
public class Navigation {
    // Auth
    private final String LOG_IN_SUCCESS = "LOG_IN_SUCCESS";
    private final String LOG_IN_FAILURE = "LOG_IN_FAILURE";
    private final String LOGOUT = "LOGOUT";
    private final String ADMIN_SUCCESS = "ADMIN";
    private final String REGISTER_SUCCESS = "REGISTER_SUCCESS";
    private final String REGISTER_FAILURE = "REGISTER_FAILURE";
    // Users
    private final String USER_TRANSACTIONS = "USER_TRANSACTIONS";
    private final String USER_TRANSFERS = "USER_TRANSFERS";
    private final String USER_REQUESTS = "USER_REQUESTS";
    // Admins
    private final String ADMIN_DASHBOARD = "ADMIN_DASHBOARD";
    private final String ADMIN_USER_TRANSACTIONS = "ADMIN_USER_TRANSACTIONS";
    private final String ADD_ADMIN = "ADD_ADMIN";
    private final String ADD_ADMIN_FAILURE = "ADD_ADMIN_FAILURE";
    private final String ADD_ADMIN_SUCCESS = "ADD_ADMIN_SUCCESS";

    public Navigation() {
    }

    
    public String getLOG_IN_SUCCESS() {
        return LOG_IN_SUCCESS;
    }

    public String getLOG_IN_FAILURE() {
        return LOG_IN_FAILURE;
    }

    public String getLOGOUT() {
        return LOGOUT;
    }

    public String getADMIN_SUCCESS() {
        return ADMIN_SUCCESS;
    }

    public String getREGISTER_SUCCESS() {
        return REGISTER_SUCCESS;
    }

    public String getREGISTER_FAILURE() {
        return REGISTER_FAILURE;
    }

    public String getUSER_TRANSACTIONS() {
        return USER_TRANSACTIONS;
    }

    public String getUSER_TRANSFERS() {
        return USER_TRANSFERS;
    }

    public String getUSER_REQUESTS() {
        return USER_REQUESTS;
    }

    public String getADMIN_DASHBOARD() {
        return ADMIN_DASHBOARD;
    }

    public String getADMIN_USER_TRANSACTIONS() {
        return ADMIN_USER_TRANSACTIONS;
    }

    public String getADD_ADMIN() {
        return ADD_ADMIN;
    }

    public String getADD_ADMIN_FAILURE() {
        return ADD_ADMIN_FAILURE;
    }

    public String getADD_ADMIN_SUCCESS() {
        return ADD_ADMIN_SUCCESS;
    }

    
}
