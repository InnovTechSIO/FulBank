package fr.innovtech.fulbank.controller;

import fr.innovtech.fulbank.entities.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShowProfile();
    }



}
