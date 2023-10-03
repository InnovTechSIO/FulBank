package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.entities.Category;
import fr.innovtech.fulbank.entities.Crypto;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieDBController {


    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();


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
                    Float currency_change = resultSet.getFloat("Currency");
                    Crypto crypto = CryptoDBController.getCrypto(resultSet.getInt("number"));

                    Category category = new Category(idcategory, wording, account_fees, ceiling_high, low_ceiling, interest_rate, currency_change, crypto);
                    categories.add(category);
                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return categories.get(0);
    }

}
