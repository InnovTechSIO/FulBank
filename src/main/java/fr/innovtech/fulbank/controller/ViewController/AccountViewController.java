package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.CategorieDBController;
import fr.innovtech.fulbank.controller.DBController.CryptoDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import fr.innovtech.fulbank.entities.Account;
import fr.innovtech.fulbank.entities.Category;
import fr.innovtech.fulbank.entities.Crypto;
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


    // Doit placer l'élément avec le nom du compte et le montant (voir la table type)
    @FXML
    protected void setCourants(){
        ArrayList<Account> accounts = AccountDBController.getAccountByIdCustomer(CustomerDBController.connectedCustomer.get_id());
        for (Account anAccount : accounts){
            if (anAccount.get_category().get_wording() == "individuel or ") {
                lbl_indiv.setText(String.format("Compte %s : %f", anAccount.get_category().get_wording(), anAccount.get_amount()));
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCourants();
    }

}
