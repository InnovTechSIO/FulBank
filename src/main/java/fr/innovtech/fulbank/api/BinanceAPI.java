package fr.innovtech.fulbank.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class BinanceAPI {
    private String cryptoSymbol;

    public BinanceAPI(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }

    public String getCryptoFormat() {
        return cryptoSymbol;
    }

    public void setCryptoFormat(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }

    public String getCryptoCurrentPrice() throws IOException {
        URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol="+this.cryptoSymbol);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        JSONObject jsonObject = new JSONObject(sb.toString());



        return jsonObject.getString("price");

    }
}
