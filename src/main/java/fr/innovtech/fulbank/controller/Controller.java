package fr.innovtech.fulbank.controller;


import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.entities.Customer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Rectangle mainRectangle;

    @FXML
    private Rectangle topPane;

    @FXML
    private ImageView logo;

    @FXML
    private Button connexionButton;


    @FXML
    protected void handleClickAction(MouseEvent event) {
        Customer customer = CustomerDBController.getCustomerById(1);
        xOffset = event.getScreenX() - (topPane.getScene().getWindow()).getX();
        yOffset = event.getScreenY() - (topPane.getScene().getWindow()).getY();
        connexionButton.setText(customer.get_firstName());
    }


    @FXML
    protected void switchSceneAccount(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    protected void switchToConnexionView(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleMovementAction(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }


}