package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.entities.Coin;
import fr.innovtech.fulbank.entities.Crypto;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CryptoDBController {

    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();


    public static Crypto getCrypto(int id) {
        ArrayList<Crypto> cryptos = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Crypto WHERE number = ?");
            stmtQuery.setInt(1, id);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int number = resultSet.getInt("number");
                    String wording = resultSet.getString("wording");
                    String libelle = resultSet.getString("libelle");

                    Crypto crypto = new Crypto(number, wording, libelle);
                    cryptos.add(crypto);
                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return cryptos.get(0);
    }

    public static void pushCrpyto(Coin coin) {

        try {
            PreparedStatement stmtInsert = mySQLConnection.prepareStatement("INSERT into Coins (id, symbol, name) VALUES (?, ?, ?)");
            stmtInsert.setString(1, coin.getId());
            stmtInsert.setString(2, coin.getSymbol());
            stmtInsert.setString(3, coin.getName());
            stmtInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
