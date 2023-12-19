package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.controller.ViewController.PaymentViewController;
import fr.innovtech.fulbank.entities.Account;
import fr.innovtech.fulbank.entities.Category;
import fr.innovtech.fulbank.entities.Customer;
import fr.innovtech.fulbank.exception.HighCeilingException;
import fr.innovtech.fulbank.exception.LowCeilingException;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AccountDBController {

    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();

    // Récupère la liste de compte d'un client avec à son id
    public static ArrayList<Account> getAccountByIdCustomer(int idCustomer) {
        ArrayList<Account> accounts = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Account WHERE idClient= ?");
            stmtQuery.setInt(1, idCustomer);
            ResultSet resultSet = stmtQuery.executeQuery();

            if (resultSet.next()) {
                do {

                    int number = resultSet.getInt("number");
                    Float amount = resultSet.getFloat("Amount");
                    Date creationDate = resultSet.getDate("creation_date");
                    Date updateDate = resultSet.getDate("modification_date");
                    Date deletionDate = resultSet.getDate("deletion_date");
                    Customer customer = CustomerDBController.getCustomerById(resultSet.getInt("idClient"));
                    Category category = CategorieDBController.getCategoryById(resultSet.getInt("idCategory"));


                    Account account = new Account(number, amount, creationDate, updateDate, deletionDate, customer, category);
                    accounts.add(account);
                } while (resultSet.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Récupère le montant d'un compte avec en paramètre l'id du client et le libelle en string du compte
    public static float getAmount(int idCustomer, String account) {
        float amount = 0;
        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select Amount from Account WHERE idClient = ? and idCategory = (select idCategory from Category where wording like ?);");

            stmtQuery.setInt(1, idCustomer);
            stmtQuery.setString(2, account);
            ResultSet resultSet = stmtQuery.executeQuery();

            while (resultSet.next()) {
                amount = resultSet.getFloat("Amount");
            }
        } catch (SQLException e) {
        }
        return amount;
    }

    // Met à jour le montant du compte qui reçoit l'argent dans une transaction (utilisé dans doPayment)
    public static boolean AddAmount(float add, int idCustomer, String account, float high_ceiling, PaymentViewController paymentViewController)  {
        float amount = getAmount(idCustomer, account);
        if (amount + add > high_ceiling) {
            paymentViewController.setLblFiniText("Vous avez dépassé le plafond haut de votre compte");
            return false;
        }
        else {
            try {
                PreparedStatement stmtQuery = mySQLConnection.prepareStatement("update Account\n" +
                        "set Amount = Amount + ?\n" +
                        "where idClient = ?\n" +
                        "and idCategory=(\n" +
                        "    select idCategory\n" +
                        "    from Category\n" +
                        "    where wording like ? );");
                stmtQuery.setFloat(1, add);
                stmtQuery.setInt(2, idCustomer);
                stmtQuery.setString(3, account);
                stmtQuery.executeUpdate();
                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static boolean SubstractAmount(float add, int idCustomer, String account, float low_ceiling, PaymentViewController paymentViewController) {

        if(getAmount(idCustomer, account) - add < low_ceiling){
            paymentViewController.setLblFiniText("Vous avez dépassé le plafond bas de votre compte");
            return false;
        }
        else {
            try {
                PreparedStatement stmtQuery = mySQLConnection.prepareStatement("update Account\n" +
                        "set Amount = Amount - ?\n" +
                        "where idClient = ?\n" +
                        "and idCategory=(\n" +
                        "    select idCategory\n" +
                        "    from Category\n" +
                        "    where wording like ? );");
                stmtQuery.setFloat(1, add);
                stmtQuery.setInt(2, idCustomer);
                stmtQuery.setString(3, account);
                stmtQuery.executeUpdate();
                return true;

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    // Crée une transaction dans la table Transaction (utilisée dans doPayment)
    public static void AddTransaction(float amount, String accountAdd, String accountSubstract, int idCustomer, int idCustomer2 , PaymentViewController paymentViewController) {
        try {

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

    // Méthode finale utilisée pour effectuer une virement
    public static void Payment(float amount, int idCustomer, String accountAdd, String accountSubstract, int idBeneficiary, float high_ceiling, float low_ceiling, PaymentViewController paymentViewController) {
        if(AddAmount(amount, idBeneficiary, accountAdd, high_ceiling, paymentViewController)){
            if(        SubstractAmount(amount, idCustomer, accountSubstract, low_ceiling, paymentViewController)){
                AddTransaction(amount, accountAdd, accountSubstract, idCustomer, idBeneficiary, paymentViewController);
                paymentViewController.setLblFiniText("");

            }
        }


    }

    // Récupère une liste de tous les noms de comptes d'un utilisateur avec son idClient en paramètre
    public static ArrayList<String> getAllAccountsNameByCustomer(int idCustomer) {
        ArrayList<String> accounts = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select wording\n" +
                    "from Category\n" +
                    "join FulBank.Account A on Category.idCategory = A.idCategory\n" +
                    "join FulBank.customer c on A.idClient = c.idClient\n" +
                    "where c.idClient = ?;");
            stmtQuery.setInt(1, idCustomer);
            ResultSet resultSet = stmtQuery.executeQuery();

            while (resultSet.next()) {
                accounts.add(resultSet.getString("wording"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }



    // Récupère le plafond haut d'un compte avec en paramètre le liblle du compte
    public static float getceiling_higher(String wording){
        float ceiling = 0;
        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select ceiling_high\n" +
                    "from Category\n" +
                    "where wording like ?;");
            stmtQuery.setString(1, wording);
            ResultSet resultSet = stmtQuery.executeQuery();

            if (resultSet.next()) {
                ceiling = resultSet.getFloat("ceiling_high");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ceiling;

    }

    // Récupère le plafond bas d'un compte avec en paramètre le libelle du compte
    public static float getlow_ceiling(String wording){
        float low_ceiling = 0;
        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select low_ceiling\n" +
                    "from Category\n" +
                    "where wording like ?;");
            stmtQuery.setString(1, wording);
            ResultSet resultSet = stmtQuery.executeQuery();

            if (resultSet.next()) {
                low_ceiling = resultSet.getFloat("low_ceiling");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return low_ceiling;
    }

    // Modifie le montant du compte crypto d'un utilisateur donné en paramètre
    public static void convertCrypto(double amount, int idCustomer){
        try{
            PreparedStatement stmtUpdate = mySQLConnection.prepareStatement("update Account\n" +
                                                                                "set Amount = Amount + ?\n" +
                                                                                "where idClient = ?\n" +
                                                                                "and idCategory = (select idCategory from Category where wording like 'Crypto');");
            stmtUpdate.setDouble(1, amount);
            stmtUpdate.setInt(2, idCustomer);

            stmtUpdate.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    




}
