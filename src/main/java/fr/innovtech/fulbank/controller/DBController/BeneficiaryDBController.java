package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BeneficiaryDBController {

    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();

    public static ArrayList<String> getBeneficaryByCustomer(int idCustomer){
        ArrayList<String> beneficiaries = new ArrayList<>();

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("select name from Beneficiary where idBeneficiary = (select idBeneficiary from Add_beneficiary where id_customer = ?);");
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
}
