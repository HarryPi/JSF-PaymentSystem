package ejb;

import entity.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getCurrencies();

    Currency get(String currencyType);
}
