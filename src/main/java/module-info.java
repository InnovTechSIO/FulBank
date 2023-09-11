module fr.innovtech.fulbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires fontawesomefx;


    opens fr.innovtech.fulbank to javafx.fxml;
    exports fr.innovtech.fulbank;
    exports fr.innovtech.fulbank.controller;
    opens fr.innovtech.fulbank.controller to javafx.fxml;
}