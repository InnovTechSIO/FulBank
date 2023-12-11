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
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Account WHERE number= ?");
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

    public static void AddAmount(float add, int idCustomer, String account){
        try{
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("update Account\n" +
                                                                                "set Amount = Amount + ?\n" +
                                                                                "where idClient = ?\n" +
                                                                                "and idCategory=(\n" +
                                                                                "    select idCategory\n" +
                                                                                "    from Category\n" +
                                                                                "    where wording like ? );");
            stmtQuery.setFloat(1, add);
            stmtQuery.setInt(2, idCustomer);
            stmtQuery.setString(3,account);
            stmtQuery.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void SubstractAmount(float add, int idCustomer, String account){
        try{
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("update Account\n" +
                                                                                "set Amount = Amount - ?\n" +
                                                                                "where idClient = ?\n" +
                                                                                "and idCategory=(\n" +
                                                                                "    select idCategory\n" +
                                                                                "    from Category\n" +
                                                                                "    where wording like ? );");
            stmtQuery.setFloat(1, add);
            stmtQuery.setInt(2, idCustomer);
            stmtQuery.setString(3,account);
            stmtQuery.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void AddTransaction(float amount, String accountAdd, String accountSubstract, int idCustomer, int idCustomer2 ){
        try{

            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("insert into Transaction (datetransaction, amount, nbTransactionType, idDab, number_1, number_2) VALUES (?,\n" +
                                                                                "?,\n" +
                                                                                "1,\n" +
                                                                                "1,\n" +
                                                                                "(select number from Account where idCategory = ( select idCategory from Category where wording like ? ) and idClient = ? ),\n" +
                                                                                "(select number from Account where idCategory = ( select idCategory from Category where wording like ? ) and idClient = ? )\n" +
                                                                                ");");
            stmtQuery.setDate(1, new java.sql.Date(new Date().getTime()));
            stmtQuery.setFloat(2, amount);
            stmtQuery.setString(3, accountSubstract);
            stmtQuery.setInt(4, idCustomer);
            stmtQuery.setString(5, accountAdd);
            stmtQuery.setInt(6, idCustomer2);
            stmtQuery.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Payment(float amount, int idCustomer, String accountAdd, String accountSubstract, int idBeneficiary){
        AddAmount(amount, idBeneficiary, accountAdd);
        SubstractAmount(amount, idCustomer, accountSubstract);
        AddTransaction(amount, accountAdd, accountSubstract, idCustomer, idBeneficiary);

    }

    public static ArrayList<String> getAllAccountsByCustomer(int idCustomer){
        ArrayList<String> accounts = new ArrayList<>();

        try
        {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select wording\n" +
                                                                                "from Category\n" +
                                                                                "join FulBank.Account A on Category.idCategory = A.idCategory\n" +
                                                                                "join FulBank.customer c on A.idClient = c.idClient\n" +
                                                                                "where c.idClient = ?;");
            stmtQuery.setInt(1, idCustomer);
            ResultSet resultSet = stmtQuery.executeQuery();

            while(resultSet.next())
            {
                accounts.add(resultSet.getString("wording"));
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return accounts;
    }

    public static float getAmount(int idCustomer, String account){
        float amount = 0;
        try
        {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select Amount from Account WHERE idClient = ? and idCategory = (select idCategory from Category where wording like ?);");

            stmtQuery.setInt(1,idCustomer);
            stmtQuery.setString(2,account);
            ResultSet resultSet = stmtQuery.executeQuery();

            while(resultSet.next()){
                amount = resultSet.getFloat("Amount");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return amount;
    }

}



