package ejb;

import entity.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getCurrencies();
    double convert(String from, String to, double amount);
    Currency get(String currencyType);
}
