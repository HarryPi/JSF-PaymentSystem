package ejb;

import entity.Currency;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CurrencyServiceBean implements CurrencyService {

    private List<Currency> currencies;

    @PostConstruct
    public void init() {
        currencies = new ArrayList<>();
        currencies.add(new Currency(0, Currency.gbPounds, "GB Pounds (£)"));
        currencies.add(new Currency(1, Currency.usDollars, "US Dollars ($)"));
        currencies.add(new Currency(2, Currency.euros, "Euros (€)"));
    }

    @Override
    public List<Currency> getCurrencies() {
        return this.currencies;
    }
}
