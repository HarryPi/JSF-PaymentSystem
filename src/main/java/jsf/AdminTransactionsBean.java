/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import NavigationConstants.Navigation;
import dto.SystemTransactionDto;
import dto.SystemUserDto;
import ejb.CurrencyService;
import ejb.TransactionService;
import ejb.UserService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author harry
 */
@Named(value = "adminTransactions")
@ViewScoped
public class AdminTransactionsBean implements Serializable {

    private SystemUserDto selectedUser;
    private List<SystemUserDto> allSimpleUsers;
    private List<SystemTransactionDto> receivedTransactions;
    private List<SystemTransactionDto> sentTransactions;
    private String currencySymbol;
    private double balance;

    @Inject
    Navigation navigation;

    @EJB
    UserService userService;

    @EJB
    TransactionService transactionService;

    @Inject
    CurrencyService currencyService;

    /**
     * Creates a new instance of AdminTransactionsBean
     */
    public AdminTransactionsBean() {
    }

    public void onLoad() {
        this.allSimpleUsers = this.userService.getAllSimpleUsers();
        this.selectedUser = this.allSimpleUsers.stream().findFirst().orElse(null);
    }

    public void seeTransactionsOfUser() {
        System.out.println("Begin Loading transactions...1");

        this.receivedTransactions = transactionService.getAllReceivedTransactions(selectedUser.getId());
        if (this.receivedTransactions != null && !this.receivedTransactions.isEmpty()) {
            this.receivedTransactions.forEach(t -> t.setTransactionParticipant(userService.findUser(t.getTransactionParticipantId()).asEntity()));
        }
        this.sentTransactions = transactionService.getAllSentTransactions(selectedUser.getId());
        if (this.sentTransactions != null && !sentTransactions.isEmpty()) {
            this.sentTransactions.forEach(t -> t.setTransactionParticipant(userService.findUser(t.getTransactionParticipantId()).asEntity()));
        }

        this.currencySymbol = this.currencyService.get(selectedUser.getAccount().getCurrency()).getDisplaySymbol();

        this.setBalance(selectedUser.getAccount().getBalance());
    }

    public SystemUserDto getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(SystemUserDto selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<SystemUserDto> getAllSimpleUsers() {
        return allSimpleUsers;
    }

    public void setAllSimpleUsers(List<SystemUserDto> allSimpleUsers) {
        this.allSimpleUsers = allSimpleUsers;
    }

    public List<SystemTransactionDto> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<SystemTransactionDto> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public List<SystemTransactionDto> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<SystemTransactionDto> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public double getBalance() {
        return Math.floor(balance * 100) / 100;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
