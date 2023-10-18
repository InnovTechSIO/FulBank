package fr.innovtech.fulbank.api;

import fr.innovtech.fulbank.annotations.NotNull;
import fr.innovtech.fulbank.controller.DBController.CryptoDBController;
import fr.innovtech.fulbank.entities.Coin;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

        double convertedAmount = cryptoPrice * amount;

        if (!currency.equalsIgnoreCase(crypto)) {
            Double currencyExchangeRate = getCryptoCurrentPrice(currency, currency.toLowerCase());

            convertedAmount /= currencyExchangeRate;
        }

        return convertedAmount;
    }

    public static void pushAllCryptos() throws IOException, URISyntaxException {

        URI uri = new URI("https://api.coingecko.com/api/v3/coins/list");
        URL url = uri.toURL();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String symbol = jsonObject.getString("symbol");
                String name = jsonObject.getString("name");

                Coin coin = new Coin(id, symbol, name);

                CryptoDBController.pushCrpyto(coin);
            }
        }

    }

    public static ArrayList<HashMap<String, Object>> getFirst100Cryptos(@NotNull String currency) throws URISyntaxException, IOException {
        URI uri = new URI(String.format("https://api.coingecko.com/api/v3/coins/markets?vs_currency=%s", currency));
        URL url = uri.toURL();
        ArrayList<HashMap<String, Object>> cryptos = new ArrayList<>();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String symbol = jsonObject.getString("symbol");
                String name = jsonObject.getString("name");
                String image = jsonObject.getString("image");
                Double currentPrice = jsonObject.getDouble("current_price");
                Double highestPrice = jsonObject.getDouble("high_24h");
                Double lowestPrice = jsonObject.getDouble("low_24h");

                HashMap<String, Object> crypto = new HashMap<>();
                crypto.put("id", id);
                crypto.put("symbol", symbol);
                crypto.put("name", name);
                crypto.put("image", image);
                crypto.put("current_price", currentPrice);
                crypto.put("high_24h", highestPrice);
                crypto.put("low_24h", lowestPrice);


                cryptos.add(crypto);

            }
        }

        return cryptos;

    }

}
