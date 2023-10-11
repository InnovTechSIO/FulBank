package fr.innovtech.fulbank.controller.DBController;


import fr.innovtech.fulbank.entities.Customer;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDBController {

    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();
    public static Customer connectedCustomer = new Customer("default", "default", "default", "default", "default", "default");


    public static Customer getCustomerById(int id) {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM customer WHERE idClient = ?");
            stmtQuery.setInt(1, id);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int idClient = resultSet.getInt("idClient");
                    String name = resultSet.getString("name");
                    String firstname = resultSet.getString("firstname");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String password = resultSet.getString("password");
                    String iban = resultSet.getString("iban");

                    Customer customer = new Customer(firstname, name, email, phone, password, iban);
                    customers.add(customer);
                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return customers.get(0);
    }


    public static boolean checkUser(String username, String password) {

        try {
            if (MySQLDBGateway.isConnected()){
                System.out.println("Connected");
            } else {
                System.out.println("Not connected");
            }


            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM customer WHERE name = ?");
            stmtQuery.setString(1, username);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    String databasePassword = resultSet.getString("password");
                    boolean isPasswordCorrect = BCrypt.checkpw(password, databasePassword);
                    if (isPasswordCorrect) {
                        String name = resultSet.getString("name");
                        String firstname = resultSet.getString("firstname");
                        String email = resultSet.getString("email");
                        String phone = resultSet.getString("phone");
                        String pass = resultSet.getString("password");
                        String iban = resultSet.getString("iban");

                        CustomerDBController.connectedCustomer = new Customer(firstname, name, email, phone, pass, iban);
                        return true;
                    }
                    else {
                        return false;
                    }
                } while(resultSet.next());
            }

        } catch (SQLException e) {

        }

        return false;
    }

    public static  boolean registerCustomer(String name, String firstName, String email, String phone, String cardNumber, String iban, String password){

        if(name.isEmpty() || firstName.isEmpty() || email.isEmpty() || phone.isEmpty() || cardNumber.isEmpty() || iban.isEmpty() || password.isEmpty()){
            return false;
        }
        else {
            String password_crypted = BCrypt.hashpw(password, BCrypt.gensalt());

            try {
                PreparedStatement stmtQuery = mySQLConnection.prepareStatement("INSERT INTO customer (name, firstname, email, phone, Identity_card_number, IBAN, password) VALUES (?, ?, ?, ?, ?, ?, ?)");
                stmtQuery.setString(1, name);
                stmtQuery.setString(2, firstName);
                stmtQuery.setString(3, email);
                stmtQuery.setString(4, phone);
                stmtQuery.setString(5, cardNumber);
                stmtQuery.setString(6, iban);
                stmtQuery.setString(7, password_crypted);
                stmtQuery.executeUpdate();

            } catch (SQLException e) {

                return false;
            }
            return true;
        }




    }
    public static String capitalizeWord(String str){
        String words[]=str.split("\\s");
        StringBuilder capitalizeWord= new StringBuilder();
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord.append(first.toUpperCase()).append(afterfirst).append(" ");
        }
        return capitalizeWord.toString().trim();
    }
    public static List<String> allcrypto(){
        List<String> cryptos = new ArrayList<>();
        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Crypto");
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    String crypto = CustomerDBController.capitalizeWord(resultSet.getString("libelle"));

                    cryptos.add(crypto);
                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return cryptos;


    }

}