package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.CategorieDBController;
import fr.innovtech.fulbank.controller.DBController.CryptoDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import fr.innovtech.fulbank.entities.Account;
import fr.innovtech.fulbank.entities.Category;
import fr.innovtech.fulbank.entities.Crypto;
import fr.innovtech.fulbank.entities.Customer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AccountViewController extends ViewController implements Initializable {

    @FXML
    private Label lbl_indiv;
    @FXML
    private ListView<String> account_view;
    @FXML
    private ListView<String> account_view1;
    @FXML
    private ListView<String> account_view2;



    // Doit placer l'élément avec le nom du compte et le montant
    @FXML
    protected void setCourants(){
        ArrayList<Account> accounts = AccountDBController.getAccountByIdCustomer(CustomerDBController.connectedCustomer.get_id());
        ArrayList<Account> comptesCourants = new ArrayList<Account>();
        ArrayList<Account> comptesEpargnes = new ArrayList<Account>();
        ArrayList<Account> comptesCredits = new ArrayList<Account>();

        for (Account anAccount : accounts){
            String type = anAccount.get_category().get_type().getWording();
            if (type.equals("Courant")){
                comptesCourants.add(anAccount);
            }
            else if (type.equals("Epargne")) {
                comptesEpargnes.add(anAccount);
            }
            else{
                comptesCredits.add(anAccount);
            }
        }
        account_view.setItems(FXCollections.observableList(showedAccounts));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCourants();
    }

}
