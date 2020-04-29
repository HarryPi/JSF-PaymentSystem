package ejb;

public interface PaymentService {
    /**
     * Makes a payment from a user to another users {@link entity.Account}
     * @param from User email to take money from
     * @param to User email to add money to
     * @param amount The amount
     * @return Returns if this action is possible to complete or not
     */
    boolean pay(String from, String to, int amount);
}
