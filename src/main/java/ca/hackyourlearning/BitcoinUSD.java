package ca.hackyourlearning;

import java.net.MalformedURLException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Handles fetching of resources and printing of data
 */
public class BitcoinUSD {
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
    public static double getUSD() {
        JsonObject jo = BitcoinUSD.fetch("https://api.coindesk.com/v1/bpi/currentprice.json");
        JsonObject bpi = jo.get("bpi").getAsJsonObject().get("USD").getAsJsonObject();

        return bpi.get("rate_float").getAsDouble();
    }
}
