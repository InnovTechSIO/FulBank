package fr.innovtech.fulbank.controller.DBController;

import fr.innovtech.fulbank.gateways.MySQLDBGateway;

import java.sql.Connection;

public class BeneficiaryDBController {

    private static final Connection mySQLConnection = MySQLDBGateway.getConnection();
    
}
