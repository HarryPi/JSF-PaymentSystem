package jsf;

import dto.SystemTransactionDto;
import dto.SystemUserDto;
import ejb.CurrencyService;
import ejb.TransactionService;
import ejb.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "transactionBean")
@ViewScoped
public class TransactionViewerBean implements Serializable {

    private int noOfPendingRequests;
    private List<SystemTransactionDto> receivedTransactions;
    private List<SystemTransactionDto> sentTransactions;
    private String currencySymbol;
    private double balance;

    @EJB
    TransactionService transactionService;

    @EJB
    UserService userService;

    @Inject
    LayoutControllerBean layout;
    
    @Inject
    CurrencyService currencyService;

    public TransactionViewerBean() {

    }


    /**
     * An event that is fired when the transaction page is loaded
     * This is fired from a remote command instead on @PostConstructor so that it can allow the page to load
     * first then show the user some indication that something is happening on the background
     *
     * @param email The {@link entity.SystemUser} username. If none provided will attempt to get from session
     */
    public void getOnload(String email) {
        System.out.println("Begin Loading transactions...1");

        SystemUserDto currentUser = this.userService.getCurrentUser();

        this.receivedTransactions = transactionService.getAllReceivedTransactions(currentUser.getId());
        this.receivedTransactions.forEach(t -> t.setTransactionParticipant(userService.findUser(t.getTransactionParticipantId()).asEntity()));

        this.sentTransactions = transactionService.getAllSentTransactions(currentUser.getId());
        this.sentTransactions.forEach(t -> t.setTransactionParticipant(userService.findUser(t.getTransactionParticipantId()).asEntity()));

        this.currencySymbol = this.currencyService.get(currentUser.getAccount().getCurrency()).getDisplaySymbol();
        this.noOfPendingRequests = this.transactionService.getNoOfPendingRequestedTransactions(currentUser.getId());
        
        this.setBalance(currentUser.getAccount().getBalance());
        this.layout.setLoading(false);
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }


    public int getNoOfPendingRequests() {
        return noOfPendingRequests;
    }

    public void setNoOfPendingRequests(int noOfPendingRequests) {
        this.noOfPendingRequests = noOfPendingRequests;
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

    public double getBalance() {
        return Math.floor(balance * 100) / 100;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
