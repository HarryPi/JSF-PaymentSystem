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

    /**
     * Request money from a user will NOT CHECK if the other user has enough money.
     * @param from User email to take money from
     * @param to User email to add money to
     * @param amount The amount
     */
    void requestMoney(String from, String to, int amount);
}
