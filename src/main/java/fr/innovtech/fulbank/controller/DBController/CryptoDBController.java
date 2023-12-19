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


    // Getter de crypto
    public static Crypto getCrypto(int id) {
        Crypto crypto = new Crypto(0,"","");

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Crypto WHERE number = ?");
            stmtQuery.setInt(1, id);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int number = resultSet.getInt("number");
                    String wording = resultSet.getString("wording");
                    String libelle = resultSet.getString("libelle");

                    crypto.setNumber(number);
                    crypto.setLibelle(wording);
                    crypto.setLibelle(libelle);

                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return crypto;
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

    public static ArrayList<String> getAllCryptoNames(){
        ArrayList<String> allCryptos = new ArrayList<>();

        try{
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT wording FROM Crypto");
            ResultSet resultSet = stmtQuery.executeQuery();

            while(resultSet.next()){
                String wording = resultSet.getString("wording");

                allCryptos.add(wording);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return allCryptos;
    }

}
