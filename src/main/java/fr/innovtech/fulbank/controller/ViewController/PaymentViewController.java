package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentViewController extends ViewController implements Initializable {

    @FXML
    private TextField txt_montant;

    @FXML
    private ComboBox cbx_depuis;

    @FXML
    private ComboBox cbx_vers;

    @FXML
    protected void switchBeneficiary(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("add_beneficiary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL var1, ResourceBundle var2) {

    }
}



