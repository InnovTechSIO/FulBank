package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.controller.DBController.BeneficiaryDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BeneficiaryViewController extends MainViewController implements Initializable {
    @FXML
    TextField txt_name;

    @FXML
    TextField txt_iban;

    @FXML
    Button bt_add;

    //fonction en cours d'écriture pour ajouter à partir de addBeneficiary dans beneficiaryDBController
    @FXML
    protected void addBeneficiary(){
        String name = txt_name.getText();
        String iban = txt_iban.getText();
        // a besoin d'une fonction qui récupère l'id du client par son iban -> customerdbconstroller
    }
}
