package ca.hackyourlearning;

import java.net.MalformedURLException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Handles fetching of resources and printing of data
 */
public class Rates {
    /**
     * Fetches a JsonObject from a url
     *
     * @param url the API url of a particular resource
     * @return JsonObject associated with that resource
     */
    public static JsonObject fetch(String url) {
        try {
            JSONGetter httpObj = new JSONGetter(url);
            String jsonResponse = httpObj.fullResponse(false);
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            return jsonObject;
        }

        catch (MalformedURLException e) {
            System.err.printf("%s%n", e.getMessage());

            return null;
        }
    }

    /**
     * Returns the USD-Bitcoin price
     *
     * @return Bitcoin to USD price
     */
    public static double bitcoinToUsd() {
        JsonObject jo = Rates.fetch("https://api.coindesk.com/v1/bpi/currentprice.json");
        JsonObject bpi = jo.get("bpi").getAsJsonObject().get("USD").getAsJsonObject();

        return bpi.get("rate_float").getAsDouble();
    }

    /**
     * Returns USD to CAD rate
     *
     * @return USD to CAD rate
     */
    public static double usdToCad() {
        JsonObject jo = Rates.fetch("https://api.coindesk.com/v1/bpi/currentprice/CAD.json");
        Double bitcoinUsd = jo.get("bpi").getAsJsonObject().get("USD").getAsJsonObject().get("rate_float")
                .getAsDouble();
        Double bitcoinCad = jo.get("bpi").getAsJsonObject().get("CAD").getAsJsonObject().get("rate_float")
                .getAsDouble();

        return (bitcoinCad / bitcoinUsd);
    }
}
