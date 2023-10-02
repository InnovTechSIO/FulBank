package fr.innovtech.fulbank.controller;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends Controller implements Initializable {

    @FXML
    private TextField IBAN;

    @FXML
    private TextField card_number;
    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        Faker faker = new Faker();
        IBAN.setText(faker.finance().iban("FR"));
        card_number.setText(faker.finance().creditCard(CreditCardType.MASTERCARD));
    }
}
