package jsf;

import ejb.TransactionService;
import entity.SystemTransactions;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "transactionBean")
@RequestScoped
public class TransactionViewerBean {

    private boolean isLoading;
    private List<SystemTransactions> systemTransactions;

    @EJB
    TransactionService transactionService;

    public TransactionViewerBean() {
        isLoading = true;
    }

    public List<SystemTransactions> getSystemTransactions() {
        return systemTransactions;
    }

    public void setSystemTransactions(List<SystemTransactions> systemTransactions) {
        this.systemTransactions = systemTransactions;
    }

    public void getOnload(String email) {
        this.systemTransactions = transactionService.getAllTransactionsForUser(email);
        this.isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
