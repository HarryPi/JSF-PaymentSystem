package api;

import entity.Currency;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/{currency1}/{currency2}/{amount}")
public class CurrencyConverterApi {

    /*US and GB*/
    private final double GBP_to_USD_rate = 1.26;
    private final double USD_to_GBP_rate = 0.79;

    /*US and EURO*/
    private final double USD_to_EURO_rate = 0.91;
    private final double EURO_to_USD_rate = 1.10;

    /*GB and EURO*/
    private final double GBP_to_EURO = 1.15;
    private final double EURO_to_GPB = 0.87;

    @GET
    public Response convert(
            @PathParam("currency1") String currencyType1,
            @PathParam("currency2") String currencyType2,
            @PathParam("amount") double amount
    ) {

        switch (currencyType1) {
            case Currency.euros:
                if (currencyType2.equals(Currency.gbPounds)) {
                    return Response
                            .status(Response.Status.OK)
                            .entity(String.valueOf(amount * EURO_to_GPB))
                            .build();
                }
                if (currencyType2.equals(Currency.usDollars)) {
                    return Response
                            .status(Response.Status.OK)
                            .entity(String.valueOf(amount * EURO_to_USD_rate))
                            .build();
                }

            case Currency.gbPounds:
                if (currencyType2.equals(Currency.euros)) {
                    return Response
                            .status(Response.Status.OK)
                            .entity(String.valueOf(amount * GBP_to_EURO))
                            .build();
                }
                if (currencyType2.equals(Currency.usDollars)) {
                    return Response
                            .status(Response.Status.OK)
                            .entity(String.valueOf(amount * GBP_to_USD_rate))
                            .build();
                }

            case Currency.usDollars:
                if (currencyType2.equals(Currency.euros)) {
                    return Response
                            .status(Response.Status.OK)
                            .entity(String.valueOf(amount * USD_to_EURO_rate))
                            .build();
                }
                if (currencyType2.equals(Currency.gbPounds)) {
                    return Response
                            .status(Response.Status.OK)
                            .entity(String.valueOf(amount * USD_to_GBP_rate))
                            .build();
                }
            default:
                // If however all fails return bad request
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(String.valueOf("Failed to find currency to convert"))
                        .build();
        }
    }
}
