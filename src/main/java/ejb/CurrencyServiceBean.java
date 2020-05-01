package ejb;

import entity.Currency;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Named("currency")
@RolesAllowed({"users", "admins"})
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

    @Override
    public double convertToCurrency(String from, String to, double amount) {
        // If same currency is provided do not make the trip to the server
        // Rather just return same amount
        if (from.equals(to)) {
            return amount;
        }
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/webapps2020/conversion/{currency1}/{currency2}/{amount}")
                .resolveTemplate("currency1", from)
                .resolveTemplate("currency2", to)
                .resolveTemplate("amount", amount)
                .request("application/json")
                .get();
        String newBalance = response.readEntity(String.class);
        
        return Double.parseDouble(newBalance);
    }
}
