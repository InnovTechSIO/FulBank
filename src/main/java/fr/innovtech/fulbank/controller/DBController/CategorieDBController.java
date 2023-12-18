package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.entities.Category;
import fr.innovtech.fulbank.entities.Crypto;
import fr.innovtech.fulbank.entities.Type;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieDBController {


    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();


    // Getter de catégorie
    public static Category getCategoryById(int id) {
        ArrayList<Category> categories = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Category WHERE idCategory = ?");
            stmtQuery.setInt(1, id);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int idcategory = resultSet.getInt("idCategory");
                    String wording = resultSet.getString("wording");
                    Float account_fees = resultSet.getFloat("account_fees");
                    Float ceiling_high = resultSet.getFloat("ceiling_high");
                    Float low_ceiling = resultSet.getFloat("low_ceiling");
                    Float interest_rate = resultSet.getFloat("interest_rate");
                    String currency_change = resultSet.getString("Currency");
                    Crypto crypto = CryptoDBController.getCrypto(resultSet.getInt("number"));
                    Type type = TypeDBController.getType(resultSet.getInt("idType"));

                    Category category = new Category(idcategory, wording, account_fees, ceiling_high, low_ceiling, interest_rate, currency_change, crypto, type);
                    categories.add(category);
                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return categories.get(0);
    }

    // Récupère une liste de compte avec en paramètre l'id d'un client
    public static ArrayList<String> getCategoryByCustomer(int idClient)
    {
        ArrayList<String> accounts = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select wording from Category\n" +
                    "join Account A on Category.idCategory = A.idCategory\n" +
                    "where A.idClient = ?;");
            stmtQuery.setInt(1, idClient);
            ResultSet resultSet = stmtQuery.executeQuery();


            while(resultSet.next()){
                accounts.add(resultSet.getString("wording"));
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return accounts;
    }

    // Récupère la monnaie d'un compte avec en paramètre le libelle du compte
    public static String getCurrency(String account){
        String currency = "";

        try{
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select Currency from Category where wording like ?");
            stmtQuery.setString(1,account);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.wasNull()){
                PreparedStatement stmtQuery2 = mySQLConnection.prepareStatement("select cr.wording\n" +
                                                                                    "from Crypto cr\n" +
                                                                                    "join FulBank.Category C on cr.number = C.number\n" +
                                                                                    "where C.wording like ?;");
                stmtQuery2.setString(1,account);
            }

            currency = resultSet.getString("currency");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currency;
    }
}
