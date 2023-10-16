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

    public static void AddAmount(int add, int idCustomer, String account){
        try{
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("update Account\n" +
                                                                                "set Amount = Amount + ?\n" +
                                                                                "where idClient = ?\n" +
                                                                                "and idCategory=(\n" +
                                                                                "    select idCategory\n" +
                                                                                "    from Category\n" +
                                                                                "    where wording like ? );");
            stmtQuery.setInt(1, add);
            stmtQuery.setInt(2, idCustomer);
            stmtQuery.setString(3,account);
            stmtQuery.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void SubstractAmount(int add, int idCustomer, String account){
        try{
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("update Account\n" +
                                                                                "set Amount = Amount - ?\n" +
                                                                                "where idClient = ?\n" +
                                                                                "and idCategory=(\n" +
                                                                                "    select idCategory\n" +
                                                                                "    from Category\n" +
                                                                                "    where wording like ? );");
            stmtQuery.setInt(1, add);
            stmtQuery.setInt(2, idCustomer);
            stmtQuery.setString(3,account);
            stmtQuery.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void AddTransaction(int amount, String accountAdd, String accountSubstract, int idCustomer ){
        try{
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("insert into Transaction (datetransaction, amount, nbTransactionType, idDab, number_1, number_2) VALUES (date(now()),\n" +
                    "                                                                                                        ?,\n" +
                    "                                                                                                        1,\n" +
                    "                                                                                                        1,\n" +
                    "                                                                                                        (select number from Account where idCategory = ( select idCategory from Category where wording like ? ) and idClient = ? ),\n" +
                    "                                                                                                        (select number from Account where idCategory = ( select idCategory from Category where wording like ? ) and idClient = ? )\n" +
                    "                                                                                                        );");
            stmtQuery.setInt(1, amount);
            stmtQuery.setString(2, accountAdd);
            stmtQuery.setInt(3, idCustomer);
            stmtQuery.setString(4, accountSubstract);
            stmtQuery.setInt(5, idCustomer);
            stmtQuery.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doPayment(int amount, int idCustomer, String accountAdd, String accountSubstract){
        AddAmount(amount, idCustomer, accountAdd);
        SubstractAmount(amount, idCustomer, accountSubstract);
        AddTransaction(amount, accountAdd, accountSubstract, idCustomer);
    }


}



