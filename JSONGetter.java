import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

//import org.json.JSONObject;
//import org.json.JSONArray;
//import org.json.*;

public class JSONGetter {

	static String getUSD() {
		String USD = "";
		try {
            URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
            HttpURLConnection url_connection = (HttpURLConnection)url.openConnection();


            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(url_connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                System.out.println(stringBuilder.toString());
                Gson gson = new Gson(); 
                String data = gson.fromJson(stringBuilder.toString(), null)
                
                USD = data.getJSONObject("bpi").getJSONObject("USD").get("rate_float").toString();
            } finally {
            	url_connection.disconnect();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
		return USD;
    }

}
