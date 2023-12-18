package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.entities.Crypto;
import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.innovtech.fulbank.entities.Type;

public class TypeDBController {
    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();


    // Getter de type
    public static Type getType(int id) {
        Type type = new Type(0,"");

        try {
            PreparedStatement stmtQuery = mySQLConnection.prepareStatement("SELECT * FROM Type WHERE idType = ?");
            stmtQuery.setInt(1, id);
            ResultSet resultSet = stmtQuery.executeQuery();

            if(resultSet.next()) {
                do {
                    int number = resultSet.getInt("idType");
                    String wording = resultSet.getString("wording");

                    type.setId(number);
                    type.setWording(wording);

                } while(resultSet.next());
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return type;
    }
}
