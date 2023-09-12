package fr.innovtech.fulbank.controller;


import fr.innovtech.fulbank.App;
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
    private FontAwesomeIcon closeButton;

    @FXML
    private FontAwesomeIcon minusButton;

    @FXML
    private FontAwesomeIcon resizeButton;


    @FXML
    private Rectangle mainRectangle;

    @FXML
    private Rectangle topPane;

    @FXML
    private ImageView logo;

    @FXML
    private Rectangle accountRectangle;

    @FXML
    protected void onCloseButtonClick() {

        ((Stage) closeButton.getScene().getWindow()).close();

    }

    @FXML
    protected void onMinusButtonClick() {

        ((Stage) closeButton.getScene().getWindow()).setIconified(true);

    }
    
    
    @FXML
    protected void onResizeButtonClick() {
        this.setFullScreen(closeButton.getScene());


    }

    @FXML
    protected void handleClickAction(MouseEvent event) {
        xOffset = event.getScreenX() - (topPane.getScene().getWindow()).getX();
        yOffset = event.getScreenY() - (topPane.getScene().getWindow()).getY();
    }


    @FXML
    protected void switchSceneAccount(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Platform.runLater(() -> this.setFullScreen(scene));
    }


    @FXML
    protected void switchToConnexionView(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Platform.runLater(() -> this.setFullScreen(scene));
    }

    @FXML
    protected void handleMovementAction(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void setFullScreen(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        stage.setFullScreen(true);
        stage.setWidth(stage.getWidth());
        stage.setHeight(stage.getHeight());
        stage.setX(0);
        stage.setY(0);
        stage.setFullScreen(false);
        if(this.topPane != null) {
            topPane.setWidth(stage.getWidth());
            topPane.setVisible(false);
        }
        if(this.mainRectangle != null) {
            mainRectangle.setWidth(stage.getWidth());
            mainRectangle.setHeight(stage.getHeight());
        }
        if(this.logo != null) {
            logo.setScaleX(2);
            logo.setScaleY(2);
            logo.setLayoutY(stage.getHeight() * 0.05);
            logo.setLayoutX(stage.getWidth() / 2 - logo.getFitWidth() / 2);
        }
        if(this.closeButton != null)
            closeButton.setVisible(false);
        if(this.minusButton != null)
            minusButton.setVisible(false);
        if(this.resizeButton != null)
            resizeButton.setVisible(false);
    }

}