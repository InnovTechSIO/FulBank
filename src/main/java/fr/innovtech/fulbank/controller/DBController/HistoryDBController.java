package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.entities.Transaction;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class HistoryDBController {


    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();

    public static List<Integer> getAllIdAccountsByCustomer(int idCustomer) {
        ArrayList<Integer> idAccounts = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT number FROM Account WHERE idClient = ?");
            stmtQuery.setInt(1, idCustomer);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int idAccount = resultSet.getInt("number");
                    idAccounts.add(idAccount);
                } while(resultSet.next());
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return idAccounts;
    }

    public static List<Transaction> getAllTransactionsByCustomer(int idCustomer) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        return transactions;
    }



}
