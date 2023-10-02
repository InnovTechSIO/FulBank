package fr.innovtech.fulbank.controller;

import fr.innovtech.fulbank.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends Controller implements Initializable {


    @FXML
    private Button profileButton;

    @FXML
    private Label customer_name;

    @FXML
    private Label iban;

    @FXML
    private Label lbl_email;

    @FXML
    protected void ShowProfile(){
        customer_name.setText(CustomerDBController.connectedCustomer.get_lastName() );
        iban.setText(CustomerDBController.connectedCustomer.get_iban());
        lbl_email.setText(CustomerDBController.connectedCustomer.get_mail());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShowProfile();
    }



}
