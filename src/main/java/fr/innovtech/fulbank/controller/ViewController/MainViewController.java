package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends ViewController implements Initializable {

    @FXML
    protected void switchProfile(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchPayment(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("payment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void logoutCustomer(MouseEvent event) throws IOException {
        CustomerDBController.connectedCustomer = CustomerDBController.defaultCustomer;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchCours(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("courscrypto.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchAccounts(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchConversion(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("conversion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchHistory(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("history.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL var1, ResourceBundle var2) {

    }
}
