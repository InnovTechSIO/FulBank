package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.BeneficiaryDBController;
import fr.innovtech.fulbank.controller.DBController.CategorieDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import fr.innovtech.fulbank.exception.HighCeilingException;
import fr.innovtech.fulbank.exception.LowCeilingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

import static java.lang.Float.parseFloat;
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
    private Label lbl_currency;

    @FXML
    private Button test;

    @FXML
    private Label label_error_payment;

    @FXML
    protected void switchBeneficiary(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("add_beneficiary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    protected void setCurrency(){
        if (cbx_depuis.getValue() != null) {
            System.out.println(CategorieDBController.getCurrency(cbx_depuis.getValue().toString()));
        }
    }

    @FXML
    protected void showCbxDepuis(){
        cbx_vers.getItems().clear();
        int idCustomer = CustomerDBController.connectedCustomer.get_id();
        cbx_depuis.getItems().addAll(CategorieDBController.getCategoryByCustomer(idCustomer));
        setCurrency();
    }

    @FXML
    protected void showCbxVers(){
        int idCustomer = CustomerDBController.connectedCustomer.get_id();
        ArrayList<String> allAccounts = new ArrayList<>(CategorieDBController.getCategoryByCustomer(idCustomer));
        allAccounts.addAll(BeneficiaryDBController.getBeneficiariesByCustomer(idCustomer));
        String choosen = cbx_depuis.getValue().toString();

        for(String account : allAccounts){
            if (!account.equals(choosen)){
                cbx_vers.getItems().add(account);
            }
        }
    }

    @FXML
    protected void doPayment() throws HighCeilingException, LowCeilingException {
        String beneficiary = cbx_vers.getValue().toString();
        String accountAdd ="";
        int idCustomer = CustomerDBController.connectedCustomer.get_id();
        int idBeneficiary = BeneficiaryDBController.getBeneficiaryByName(cbx_vers.getValue().toString());
        float amount = parseFloat(txt_montant.getCharacters().toString().replaceAll(",","."));
        ArrayList<String> accounts = AccountDBController.getAllAccountsNameByCustomer(idCustomer);
        if (!accounts.contains(beneficiary)){
            accountAdd = "courant";

        }
        else {
            accountAdd = beneficiary;
            idBeneficiary = idCustomer;
        }
        String accountSubstract = cbx_depuis.getValue().toString();
        float high_ceiling  = AccountDBController.getceiling_higher(accountAdd);
        float low_ceiling = AccountDBController.getlow_ceiling(accountSubstract);
        AccountDBController.Payment(amount, idCustomer, accountAdd, accountSubstract, idBeneficiary, high_ceiling, low_ceiling, this);

    }
    public void setLblFiniText(String text){
        label_error_payment.setVisible(true);
        label_error_payment.setText(text);
    }
    



    boolean active = false;

    public void setTextLblFini(String text, Color color){
        if(color == null)
            color = Color.BLACK;
        if(active) {
            label_error_payment.setTextFill(color);
            label_error_payment.setText(text);
            label_error_payment.setVisible(true);


        }
    }

    public boolean isvisibilelbl(){
        return label_error_payment.isVisible();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        active = true;
    }




}



