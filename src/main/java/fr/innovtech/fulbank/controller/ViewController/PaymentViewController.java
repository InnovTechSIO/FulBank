package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.controller.DBController.CategorieDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentViewController extends ViewController implements Initializable {

    @FXML
    private TextField txt_montant;

    @FXML
    private ComboBox cbx_depuis;

    @FXML
    private ComboBox cbx_vers;

    @FXML
    private Label lbl_depuis;

    @FXML
    protected void switchBeneficiary(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("add_beneficiary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /*Fonction pour mettre a jour le cbbox grave à la collection de libelle de compte*/
    @FXML protected void showCbxDepuis(){
        ObservableList<String> accounts
                = FXCollections.observableArrayList(CategorieDBController.getCategoryByCustomer(CustomerDBController.connectedCustomer.get_id()));
        cbx_depuis.setItems(accounts);
        cbx_depuis.getSelectionModel().select(1);
    }
    /* fonction test pour voir si la récupération des libellés de compte marche*/
    @FXML
    protected void test(){
        ArrayList<String> accounts = CategorieDBController.getCategoryByCustomer(CustomerDBController.connectedCustomer.get_id());
        for (String s : accounts){
            lbl_depuis.setText(s);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }




}



