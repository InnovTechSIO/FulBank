package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.controller.DBController.CategorieDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import fr.innovtech.fulbank.controller.DBController.HistoryDBController;

import fr.innovtech.fulbank.entities.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryViewController extends ViewController implements Initializable {

    @FXML
    ListView<Transaction> historyListView;

    @FXML
    ComboBox<String> cbx_choiceAccount;

    @FXML
    Button btn_TestHistory;


    @FXML
    protected void showCbxChoiceAccount() {
        cbx_choiceAccount.getItems().clear();
        int idCustomer = CustomerDBController.connectedCustomer.get_id();
        cbx_choiceAccount.getItems().addAll(CategorieDBController.getCategoryByCustomer(idCustomer));
    }

    @FXML
    protected void Test(){
        Integer idCustomer = CustomerDBController.connectedCustomer.get_id();
        List<Integer> allIdAccount = HistoryDBController.getAllIdAccountsByCustomer(idCustomer);
        for(Integer idAccount : allIdAccount){
            System.out.println(idAccount);
        }


    }

    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        showCbxChoiceAccount();

    }
}
