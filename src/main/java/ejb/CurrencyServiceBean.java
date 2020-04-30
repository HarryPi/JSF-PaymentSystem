package ejb;

import entity.Currency;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CurrencyServiceBean implements CurrencyService {

    private List<Currency> currencies;

    @PostConstruct
    public void init() {
        currencies = new ArrayList<>();
        currencies.add(new Currency(0, Currency.gbPounds, "GB Pounds (£)", "£"));
        currencies.add(new Currency(1, Currency.usDollars, "US Dollars ($)", "$"));
        currencies.add(new Currency(2, Currency.euros, "Euros (€)", "€"));
    }

    @Override
    public List<Currency> getCurrencies() {
        return this.currencies;
    }

    @Override
    public double convert(String from, String to, double amount) {
        Client client = ClientBuilder.newClient();
        Response res = client.target("http://localhost:8080/webapps2020/conversion/{currency1}/{currency2}/{amount}")
                .queryParam("currency1", from)
                .queryParam("currency2", to)
                .queryParam("amount", amount)
                .request("applicaiton/json")
                .get();

        return Double.parseDouble(res.getEntity().toString());
    }

    @Override
    public Currency get(String currencyType) {
        switch (currencyType) {
            case Currency.euros:
                return this.currencies.get(2);
            case Currency.usDollars:
                return this.currencies.get(1);
            default:
                return this.currencies.get(0);
        }
    }
}
