package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.entities.Account;
import fr.innovtech.fulbank.entities.Category;
import fr.innovtech.fulbank.entities.Customer;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AccountDBController {

    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();

    public static Account getAccountById(int id) {
        ArrayList<Account> accounts = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Account WHERE idAccount = ?");
            stmtQuery.setInt(1, id);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {

                    int number = resultSet.getInt("number");
                    Float amount = resultSet.getFloat("Amount");
                    Date creationDate = resultSet.getDate("creation_date");
                    Date updateDate = resultSet.getDate("modification_date");
                    Date deletionDate = resultSet.getDate("deletion_date");
                    Customer customer = CustomerDBController.getCustomerById(resultSet.getInt("idCustomer"));
                    Category category = CategorieDBController.getCategoryById(resultSet.getInt("idCategory"));


                    Account account = new Account(number, amount, creationDate, updateDate, deletionDate, customer, category);
                    accounts.add(account);
                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return accounts.get(0);
    }

}



