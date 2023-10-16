package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.BeneficiaryDBController;
import fr.innovtech.fulbank.controller.DBController.CategorieDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.SplittableRandom;

import static java.lang.Integer.parseInt;

public class PaymentViewController extends ViewController implements Initializable {

    @FXML
    private TextField txt_montant;

    @FXML
    private ComboBox cbx_depuis;

    @FXML
    private ComboBox cbx_vers;

    @FXML
    private Label lbl_depuis;

    @FXML
    private Button bt_payment;

    @FXML
    private ImageView plusButton;

    @FXML
    protected void switchBeneficiary(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("add_beneficiary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void showCbxDepuis(){
        cbx_vers.getItems().clear();
        int idCustomer = CustomerDBController.connectedCustomer.get_id();
        cbx_depuis.getItems().addAll(CategorieDBController.getCategoryByCustomer(idCustomer));
    }

    @FXML
    protected void showCbxVers(){
        int idCustomer = CustomerDBController.connectedCustomer.get_id();
        ArrayList<String> allAccounts = new ArrayList<>(CategorieDBController.getCategoryByCustomer(idCustomer));
        allAccounts.addAll(BeneficiaryDBController.getBeneficaryByCustomer(idCustomer));
        String choosen = cbx_depuis.getValue().toString();

        for(String account : allAccounts){
            if (!account.equals(choosen)){
                cbx_vers.getItems().add(account);
            }
        }
    }

    @FXML
    protected void doPayment(){
        int idCustomer = CustomerDBController.connectedCustomer.get_id();
        int amount = parseInt(txt_montant.getCharacters().toString());
        String accountAdd = cbx_vers.getValue().toString();
        String accountSubstract = cbx_depuis.getValue().toString();
        AccountDBController.doPayment(amount, idCustomer, accountAdd, accountSubstract);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }




}



