package fr.innovtech.fulbank.controller;

import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDBController {
    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();

    public static List<String> getAllCateg(){
        List<String> categs = new ArrayList<>();
        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Category");
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    String categ = CustomerDBController.capitalizeWord(resultSet.getString("wording"));

                    categs.add(categ);
                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return categs;
    }




}
