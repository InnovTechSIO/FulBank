package fr.innovtech.fulbank.api;

import fr.innovtech.fulbank.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoinGeckoAPI {

    public static Double getCryptoCurrentPrice(@NotNull String crypto, @NotNull String currency) throws IOException {
        URL url = new URL(String.format("https://api.coingecko.com/api/v3/coins/%s", crypto.toLowerCase()));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        JSONObject jsonObject = new JSONObject(sb.toString());

        return jsonObject.getJSONObject("market_data")
                .getJSONObject("current_price")
                .getDouble(currency);

    }

    public static Double convertCryptoToCurrency(@NotNull String crypto, @NotNull String currency, Double amount) throws IOException {
        Double cryptoPrice = getCryptoCurrentPrice(crypto, currency);

        Double convertedAmount = cryptoPrice * amount;

        if (!currency.equalsIgnoreCase(currency)) {
            Double currencyExchangeRate = getCryptoCurrentPrice(currency, currency.toLowerCase());

            convertedAmount /= currencyExchangeRate;
        }

        return convertedAmount;
    }



}
