package jsf;

import dto.SystemTransactionDto;
import ejb.TransactionService;
import ejb.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.MethodExpression;
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

    @PostConstruct
    public void displaySidebar() {
        layout.setShouldShowSidebar(true);
    }

    public TransferRequestsBean() {
    }


    public void loadAllRequests() {
        this.transactions = this.transactionService
                .getPendingRequestedTransactions(userService.getCurrentUser().getId());
    }

    public void approveSelected() {
        transactionService.approveTransactionRequest(selectedTransactions);
        this.loadAllRequests();
        this.selectedTransactions = new ArrayList<>();
        this.layout.displayFacesMessage("Money Trasnfered!", "All selected requests where approved", FacesMessage.SEVERITY_INFO);
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
