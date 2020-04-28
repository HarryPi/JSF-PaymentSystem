package ejb;

public interface PaymentService {
    void pay(String from, String to, int amount);
}
