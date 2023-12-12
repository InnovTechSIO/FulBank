package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BeneficiaryDBController {

    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();

    public static ArrayList<String> getBeneficiariesByCustomer(int idCustomer){
        ArrayList<String> beneficiaries = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select name from Beneficiary where idClient = ?;");
            stmtQuery.setInt(1, idCustomer);
            ResultSet resultSet = stmtQuery.executeQuery();


            while(resultSet.next()){
                beneficiaries.add(resultSet.getString("name"));
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return beneficiaries;
    }

    public static int getBeneficiaryByName(String nameBeneficiary) {
        int idBeneficiary = 0;
        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select idClientBeneficiary from Beneficiary where name = ?");
            stmtQuery.setString(1, nameBeneficiary);
            ResultSet resultSet = stmtQuery.executeQuery();

            if (resultSet.next()) {
                idBeneficiary = resultSet.getInt("idClientBeneficiary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idBeneficiary;
    }

    public static boolean doExist(String name, String iban){
        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select idClientBeneficiary from Beneficiary where name = ? and idClientBeneficiary = (select customer.idClient from customer where customer.IBAN = ?);");
            stmtQuery.setString(1, name);
            stmtQuery.setString(2, iban);
            ResultSet resultSet = stmtQuery.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Fonction en cours pour ajouter un bénéficiaire à partir de son iban (on lui crée un nom) -> on vérifie d'abord s'il existe avec la fonction doExist
    // A vérifier
    public static void addBeneficiary(String name, String iban, int idClient, int idBeneficiary){
        if (!doExist(name,iban)){
            try{
                PreparedStatement stmtQuery = mySQLConnection.prepareStatement("insert into Beneficiary (idClient, idClientBeneficiary, name) VALUES (?, ?, ?);");
                stmtQuery.setInt(1, idClient);
                stmtQuery.setInt(2, idBeneficiary);
                stmtQuery.setString(3,name);

                stmtQuery.executeUpdate();

            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


}
