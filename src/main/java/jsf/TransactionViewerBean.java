package jsf;

import dto.SystemTransactionDto;
import dto.SystemUserDto;
import ejb.CurrencyService;
import ejb.TransactionService;
import ejb.UserService;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "transactionBean")
@ViewScoped
public class TransactionViewerBean implements Serializable {

    private List<SystemTransactionDto> transactionsDto;
    private String currencySymbol;

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

    public List<SystemTransactionDto> getTransactionsDto() {
        return transactionsDto;
    }

    public void setTransactionsDto(List<SystemTransactionDto> transactionsDto) {
        this.transactionsDto = transactionsDto;
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

        SystemUserDto currentUser = this.userService.getCurrentUser();
        this.transactionsDto = transactionService.getAllTransactionsForUser(email);
        this.transactionsDto.forEach(t -> t.setTransactionParticipant(userService.findUser(t.getTransactionParticipantId())));
        this.currencySymbol = this.currencyService.get(currentUser.getAccount().getCurrency()).getDisplaySymbol();
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
