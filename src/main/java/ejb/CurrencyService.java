package ejb;

import entity.Currency;

import java.util.List;

public interface CurrencyService {
    public List<Currency> getCurrencies();
}
