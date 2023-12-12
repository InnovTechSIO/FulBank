package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountViewController extends ViewController implements Initializable {

    @FXML
    private Label lbl_indiv;

    @FXML
    protected void setLbl_indiv(){
        float amount = AccountDBController.getAmount(CustomerDBController.connectedCustomer.get_id(), "courant");
        lbl_indiv.setText(String.valueOf(amount));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLbl_indiv();
    }

}
