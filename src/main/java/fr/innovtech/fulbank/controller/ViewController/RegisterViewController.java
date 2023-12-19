package fr.innovtech.fulbank.controller.ViewController;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterViewController extends ViewController implements Initializable {

    @FXML
    private TextField IBAN;

    @FXML
    private TextField card_number;

    @FXML
    private ComboBox wording;

    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        Faker faker = new Faker();
        IBAN.setText(faker.finance().iban("FR"));
        card_number.setText(faker.finance().creditCard(CreditCardType.MASTERCARD));


    }




}
