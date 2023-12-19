package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.CategorieDBController;
import fr.innovtech.fulbank.controller.DBController.CryptoDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import fr.innovtech.fulbank.entities.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;


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
    @FXML
    Button bt_validate;

    public void showCbbox(){
        cbbox2.setItems(FXCollections.observableList(CryptoDBController.getAllCryptoNames()));
        cbbox1.setItems(FXCollections.observableList(CategorieDBController.getAllCurrencies()));
    }

    public void changeCbbox(){

        ObservableList<String> items = FXCollections.observableArrayList();
        items = cbbox1.getItems();
        cbbox1.setItems(cbbox2.getItems());
        cbbox2.setItems(items);
    }

    public void conversion(){
        double amount = parseFloat(txt_value1.getText());
        String currency1 = cbbox1.getValue().toString().toLowerCase();
        String currency2 = cbbox2.getValue().toString().toLowerCase();
        ArrayList<HashMap<String, Object>> cryptosData = App.cryptosData;
        for(HashMap<String, Object> data : cryptosData){
            if(data.get("symbol").equals(currency1) || data.get("symbol").equals(currency2)){
                if (currency1.equals("euros")){
                    txt_value2.setText(String.valueOf(amount / (Double) data.get("current_price")));
                }
                else {
                    txt_value2.setText(String.valueOf(amount * (Double) data.get("current_price")));
                }
            }
        }
        bt_validate.setVisible(true);
    }

    public void validate(){
        AccountDBController.convertCrypto(parseDouble(txt_value2.getText()), CustomerDBController.connectedCustomer.get_id());
        AccountDBController.AddAmount(parseFloat(txt_value2.getText()), CustomerDBController.connectedCustomer.get_id(),"courant",AccountDBController.getceiling_higher("courant"))
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCbbox();
    }

}