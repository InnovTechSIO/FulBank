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
import fr.innovtech.fulbank.elements.AccountCard;
import fr.innovtech.fulbank.elements.AccountCardCell;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountViewController extends ViewController implements Initializable {

    @FXML
    private Label lbl_indiv;
    @FXML
    private ListView<AccountCard> account_view1;
    @FXML
    private ListView<AccountCard> account_view2;
    @FXML
    private ListView<AccountCard> account_view3;



    // Doit placer l'élément avec le nom du compte et le montant
    @FXML
    protected void showAccounts() {
        ArrayList<Account> accounts = AccountDBController.getAccountByIdCustomer(CustomerDBController.connectedCustomer.get_id());
        List<AccountCard> accountCards1 = new ArrayList<>();
        List<AccountCard> accountCards2 = new ArrayList<>();
        List<AccountCard> accountCards3 = new ArrayList<>();

        for(Account anAccount : accounts){
            String name = anAccount.get_category().get_wording();
            float amount = anAccount.get_amount();
            String type = anAccount.get_category().get_type().getWording();

            AccountCard accountCard = new AccountCard(name,amount,type);

            if (type.equals("Courant")){
                accountCards1.add(accountCard);
            } else if (type.equals("Epargne")) {
                accountCards2.add(accountCard);
            }
            else{
                accountCards3.add(accountCard);
            }
        }

        account_view1.setItems(FXCollections.observableList(accountCards1));
        account_view1.setCellFactory(param -> new AccountCardCell());

        account_view2.setItems(FXCollections.observableList(accountCards2));
        account_view2.setCellFactory(param -> new AccountCardCell());

        account_view3.setItems(FXCollections.observableList(accountCards3));
        account_view3.setCellFactory(param -> new AccountCardCell());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAccounts();
    }

}
