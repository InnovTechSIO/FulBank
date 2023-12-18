package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.entities.DAB;
import fr.innovtech.fulbank.entities.Transaction;
import fr.innovtech.fulbank.entities.Transactiontype;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    public static List<Transaction> getAllTransactionsByAccountSubstract(Integer idAccount) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Date dateTransaction = new Date();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT T.idtransaction, T.datetransaction, T.amount,T.number_1,T.number_2,c.firstname as name, A.number FROM Transaction T JOIN FulBank.Account A on A.number = T.number_1" +
                    " JOIN FulBank.customer c on A.idClient = c.idClient" +
                    " WHERE T.number_1 = ? ORDER BY datetransaction DESC");
            stmtQuery.setInt(1, idAccount);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int idTransaction = resultSet.getInt("idTransaction");
                    int idAccountTransaction = resultSet.getInt("A.number");

                    float amount = resultSet.getFloat("amount");

                    String date = resultSet.getString("datetransaction");
                    String name = resultSet.getString("name");
                    int idaccountadd = resultSet.getInt("number_2");
                    int idaccountsubstract = resultSet.getInt("number_1");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        dateTransaction = dateFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Transactiontype transactiontype = new Transactiontype(1, "virement");
                    DAB dab = new DAB(1,"1.5", "48.450001");



                    Transaction transaction = new Transaction(idTransaction,dateTransaction,amount, AccountDBController.getAccountByIdd(idaccountsubstract),AccountDBController.getAccountByIdd(idaccountadd), transactiontype, dab);
                    transactions.add(transaction);
                } while(resultSet.next());
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public static List<Transaction> getAllTransactionsByAccountSubstractByCategorie(int idAccount, String categorie){
        ArrayList<Transaction> transactions = new ArrayList<>();
        Date dateTransaction = new Date();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT T.idtransaction, T.datetransaction, T.amount,T.number_1,T.number_2,c.firstname as name, A.number FROM Transaction T JOIN FulBank.Account A on A.number = T.number_1" +
                    " JOIN FulBank.customer c on A.idClient = c.idClient" +
                    " JOIN FulBank.Category C2 on C2.idCategory = A.idCategory" +
                    " WHERE T.number_1 = ? AND C2.wording=?  ORDER BY datetransaction DESC");

            stmtQuery.setInt(1, idAccount);
            stmtQuery.setString(2, categorie);

            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int idTransaction = resultSet.getInt("idTransaction");
                    int idAccountTransaction = resultSet.getInt("A.number");
                    float amount = resultSet.getFloat("amount");
                    int idaccountadd = resultSet.getInt("number_2");
                    int idaccountsubstract = resultSet.getInt("number_1");

                    String date = resultSet.getString("datetransaction");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        dateTransaction = dateFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Transactiontype transactiontype = new Transactiontype(1, "virement");
                    DAB dab = new DAB(1,"1.5", "48.450001");



                    Transaction transaction = new Transaction(idTransaction,dateTransaction,amount, AccountDBController.getAccountByIdd(idaccountsubstract),AccountDBController.getAccountByIdd(idaccountadd), transactiontype, dab);
                    transactions.add(transaction);
                } while(resultSet.next());
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return transactions;

    }

    public static List<Transaction> getAllTransactionsByAccountAdd(Integer idAccount) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Date dateTransaction = new Date();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT T.idtransaction, T.datetransaction, T.amount,T.number_1,T.number_2,c.firstname as name, A.number FROM Transaction T JOIN FulBank.Account A on A.number = T.number_2" +
                    " JOIN FulBank.customer c on A.idClient = c.idClient" +
                    " WHERE T.number_2 = ? ORDER BY datetransaction DESC");
            stmtQuery.setInt(1, idAccount);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int idTransaction = resultSet.getInt("idTransaction");
                    int idAccountTransaction = resultSet.getInt("A.number");
                    float amount = resultSet.getFloat("amount");
                    int idaccountadd = resultSet.getInt("number_2");
                    int idaccountsubstract = resultSet.getInt("number_1");

                    String date = resultSet.getString("datetransaction");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        dateTransaction = dateFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Transactiontype transactiontype = new Transactiontype(1, "virement");
                    DAB dab = new DAB(1,"1.5", "48.450001");



                    Transaction transaction = new Transaction(idTransaction,dateTransaction,amount, AccountDBController.getAccountByIdd(idaccountsubstract),AccountDBController.getAccountByIdd(idaccountadd), transactiontype, dab);
                    transactions.add(transaction);
                } while(resultSet.next());
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public static List<Transaction> getAllTransactionsByAccountAddByCategorie(int idAccount, String categorie){
        ArrayList<Transaction> transactions = new ArrayList<>();
        Date dateTransaction = new Date();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT T.idtransaction, T.datetransaction, T.amount,T.number_1,T.number_2,c.firstname as name, A.number FROM Transaction T JOIN FulBank.Account A on A.number = T.number_2" +
                    "" +
                    " JOIN FulBank.customer c on A.idClient = c.idClient" +
                    " JOIN FulBank.Category C2 on C2.idCategory = A.idCategory" +
                    " WHERE T.number_2 = ? AND C2.wording=?  ORDER BY datetransaction DESC");
            stmtQuery.setInt(1, idAccount);
            stmtQuery.setString(2, categorie);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int idTransaction = resultSet.getInt("idTransaction");
                    int idAccountTransaction = resultSet.getInt("A.number");
                    float amount = resultSet.getFloat("amount");
                    int idaccountadd = resultSet.getInt("number_2");
                    int idaccountsubstract = resultSet.getInt("number_1");

                    String date = resultSet.getString("datetransaction");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        dateTransaction = dateFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Transactiontype transactiontype = new Transactiontype(1, "virement");
                    DAB dab = new DAB(1,"1.5", "48.450001");



                    Transaction transaction = new Transaction(idTransaction,dateTransaction,amount, AccountDBController.getAccountByIdd(idaccountsubstract),AccountDBController.getAccountByIdd(idaccountadd), transactiontype, dab);
                    transactions.add(transaction);
                } while(resultSet.next());
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return transactions;

    }





}
