package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import fr.innovtech.fulbank.entities.Account;
import fr.innovtech.fulbank.entities.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AccountViewController extends ViewController implements Initializable {

    @FXML
    private Label lbl_indiv;


    @FXML
    protected void setLbl_indiv(){
        float amount = AccountDBController.getAmount(CustomerDBController.connectedCustomer.get_id(), "courant");
        lbl_indiv.setText(String.valueOf(amount));
    }

    @FXML
    protected void setCourants(){
        //ArrayList<> accounts = AccountDBController.getAllAccountsByCustomer(CustomerDBController.connectedCustomer.get_id());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLbl_indiv();
    }

}
