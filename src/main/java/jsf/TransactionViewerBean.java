package jsf;

import ejb.TransactionService;
import entity.SystemTransactions;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "transactionBean")
@RequestScoped
public class TransactionViewerBean {

    private List<SystemTransactions> systemTransactions;

    @EJB
    TransactionService transactionService;

    @Inject
    LayoutControllerBean layout;

    public TransactionViewerBean() {

    }

    public List<SystemTransactions> getSystemTransactions() {
        return systemTransactions;
    }

    public void setSystemTransactions(List<SystemTransactions> systemTransactions) {
        this.systemTransactions = systemTransactions;
    }

    /**
     * An event that is fired when the transaction page is loaded
     * It retrieves the transactions of the user
     *
     * @param email The {@link entity.SystemUser} username. If none provided will attempt to get from session
     */
    public void getOnload(String email) {
        System.out.println("Begin Loading transactions...1");
        if (email.isEmpty()) {
            // This occures when a refresh happens and we were not redirected from login or register forms
            // In this case a user already exists in the session
            // Retrieve that user
            email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        }
        this.systemTransactions = transactionService.getAllTransactionsForUser(email);
    }

}
