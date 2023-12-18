package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ConversionViewController extends MainViewController implements Initializable {

    @FXML
    TextField txt_value1;
    @FXML
    TextField txt_value2;

    @FXML
    Button bt_conversion;

    @FXML
    ComboBox cbbox1;
    @FXML
    ComboBox cbbox2;

    public void showCrypto(){
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}