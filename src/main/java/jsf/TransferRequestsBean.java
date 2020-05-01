package jsf;

import NavigationConstants.Navigation;
import dto.SystemTransactionDto;
import dto.SystemUserDto;
import ejb.CurrencyService;
import ejb.TransactionService;
import ejb.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class TransferRequestsBean implements Serializable {

    private List<SystemTransactionDto> transactions;
    private List<SystemTransactionDto> selectedTransactions;

    @EJB
    TransactionService transactionService;

    @EJB
    UserService userService;

    @Inject
    LayoutControllerBean layout;

    @Inject
    CurrencyService currencyService;
    
    @Inject
    Navigation navigation;
    
    @PostConstruct
    public void displaySidebar() {
        layout.setShouldShowSidebar(true);
    }

    public TransferRequestsBean() {
    }

    public void loadAllRequests() {
        SystemUserDto currentUser = userService.getCurrentUser();
        this.transactions = this.transactionService
                .getPendingRequestedTransactions(currentUser.getId());
    }

    public void approveSelected() {
        transactionService.approveTransactionRequest(selectedTransactions);
        this.loadAllRequests();
        this.selectedTransactions = new ArrayList<>();
        this.layout.displayFacesMessage("Money Trasnfered!", "All selected requests where approved", FacesMessage.SEVERITY_INFO);
        
        SystemUserDto currentUser = userService.getCurrentUser();
        
    }

    public void rejectSelected() {
        transactionService.rejectTransactionRequest(selectedTransactions);
        this.loadAllRequests();
        this.selectedTransactions = new ArrayList<>();
        this.layout.displayFacesMessage("Requests removed!", "All selected requests where removed and declined", FacesMessage.SEVERITY_INFO);
    }

    public List<SystemTransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<SystemTransactionDto> transactions) {
        this.transactions = transactions;
    }

    public List<SystemTransactionDto> getSelectedTransactions() {
        return selectedTransactions;
    }

    public void setSelectedTransactions(List<SystemTransactionDto> selectedTransactions) {
        this.selectedTransactions = selectedTransactions;
    }

}
