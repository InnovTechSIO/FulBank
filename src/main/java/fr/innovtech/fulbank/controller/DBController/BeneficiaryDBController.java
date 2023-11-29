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

            idBeneficiary = resultSet.getInt("idClientBeneficiary");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idBeneficiary;
    }
}
