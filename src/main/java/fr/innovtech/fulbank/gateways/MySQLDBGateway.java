package fr.innovtech.fulbank.gateways;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBGateway {
    private static Connection connection;
    private static String url_base = "jdbc:mariadb://", host = "172.16.119.4/FulBank?characterEncoding=utf8&autoReconnect=true&useSSL=false&serverTimezone=UTC",
            username = "fulbank", password = "FulbankADMIN";

    public static Connection getConnection() {
        return connection;
    }

    public static boolean isConnected() {
        try {
            if ((connection == null) || (connection.isClosed())) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void connection() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection(url_base + host, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deconnection() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}