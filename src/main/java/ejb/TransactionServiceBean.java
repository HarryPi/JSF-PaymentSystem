package ejb;

import dao.SystemTransaction.SystemTransactionDao;
import dto.SystemTransactionDto;
import dto.SystemUserDto;
import entity.SystemTransaction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TransactionServiceBean implements TransactionService {

    @EJB
    SystemTransactionDao transactionDao;

    @EJB
    UserService userService;

    @EJB
    PaymentService paymentService;

    public TransactionServiceBean() {
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<SystemTransactionDto> getAllReceivedTransactions(long id) {
        SystemUserDto user = userService.findUser(id);
        // This could have done by email but assuming transactions are an insane ammount
        // It would be faster to do this query by the ID which is index and well worth the double query

        return SystemTransaction.asDto(transactionDao.getAllReceivedTransactions(user.getId()));
    }

    @Override
    public List<SystemTransactionDto> getAllSentTransactions(long id) {
        return SystemTransaction.asDto(transactionDao.getAllSentTransactions(id));
    }

    @Override
    public List<SystemTransactionDto> getPendingRequestedTransactions(long userId) {
        return this.transactionDao
                .getAllReceivedRequests(userId)
                .stream()
                .filter(t -> t.getStatus() == TransactionStatus.REQUEST)
                .map(t -> {
                    SystemTransactionDto dto = t.asDto();
                    SystemUserDto user = userService.findUser(t.getTransactionParticipantId());
                    dto.setTransactionParticipant(user);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public int getNoOfPendingRequestedTransactions(long userId) {
        return this.transactionDao
                .getAllReceivedRequests(userId)
                .stream()
                .filter(t -> t.getStatus() == TransactionStatus.REQUEST)
                .toArray()
                .length;
    }

    @Override
    public void approveTransactionRequest(List<SystemTransactionDto> transactions) {
        transactions.forEach(transaction -> {
            SystemUserDto sendUser = userService.getCurrentUser();
            SystemUserDto receiveUser = userService.findUser(transaction.getTransactionParticipantId());

            paymentService.pay(sendUser.getUsername(), receiveUser.getUsername(), transaction.getAmount());
            transactionDao.remove(transaction.asEntity().getId());
        });
    }

    @Override
    public void rejectTransactionRequest(List<SystemTransactionDto> transactions) {

    }

}
