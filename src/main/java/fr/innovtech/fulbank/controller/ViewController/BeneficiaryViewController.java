package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.controller.DBController.BeneficiaryDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import fr.innovtech.fulbank.entities.Customer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class BeneficiaryViewController extends MainViewController implements Initializable {
    @FXML
    TextField txt_name;

    @FXML
    TextField txt_iban;

    @FXML
    Button bt_add;

    @FXML
    Label lbl_msg;
    

    //Ajout d'un bénéficiaire avec un nom et un iban
    @FXML
    protected void addBeneficiary(){
        lbl_msg.setText("");
        String name = txt_name.getText();
        String iban = txt_iban.getText();
        Customer user = CustomerDBController.connectedCustomer;
        int idBeneficiary = CustomerDBController.getCustomerByIban(iban);
        boolean benefeciaryAlreadyExist = false;

        for(Integer id : BeneficiaryDBController.getAllBeneficiary()) {
            if (idBeneficiary == id) {
                benefeciaryAlreadyExist = true;
            }
        }

        String msg = "";
        if (idBeneficiary != 0){
            if (benefeciaryAlreadyExist){
                msg = "Ce bénéficiaire est déjà enregistré";
            }
            else{
                System.out.println("test");
                BeneficiaryDBController.addBeneficiary(name, iban, user.get_id(), idBeneficiary);
                msg = "Bénéficiaire ajouté";
                txt_iban.setText("");
                txt_name.setText("");
            }
        }
        else{
            msg = "Ce bénéficiaire n'existe pas, vérifiez l'IBAN";
        }

        lbl_msg.setText(msg);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
