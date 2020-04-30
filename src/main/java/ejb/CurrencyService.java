package ejb;

import entity.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getCurrencies();
    /**
     * Converts from one currency to another
     * If same currency is provided the same amount will be returned
     * 
     * @param from {@link Currency} to convert from
     * @param to {@link Currency} to convert to
     * @param amount Amount to convert
     * @return returns the converted amount
     */
    double convertToCurrency(String from, String to, double amount);
    Currency get(String currencyType);
}
