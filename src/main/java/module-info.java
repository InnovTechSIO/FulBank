module fr.innovtech.fulbank {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.innovtech.fulbank to javafx.fxml;
    exports fr.innovtech.fulbank;
}