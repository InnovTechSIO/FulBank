module fr.innovtech.fulbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires fontawesomefx;
    requires java.sql;
    requires jbcrypt;
    requires javafaker;


    opens fr.innovtech.fulbank to javafx.fxml;
    exports fr.innovtech.fulbank;
    exports fr.innovtech.fulbank.controller.ViewController;
    exports fr.innovtech.fulbank.controller.DBController;
    opens fr.innovtech.fulbank.controller.DBController to javafx.fxml;
    opens fr.innovtech.fulbank.controller.ViewController to javafx.fxml;
}