package fr.innovtech.fulbank.controller;


import javafx.fxml.FXML;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller {


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private FontAwesomeIcon closeButton;


    @FXML
    private Rectangle mainRectangle;

    @FXML
    private Rectangle topPane;

    @FXML
    protected ImageView logo;

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
        Stage s = (Stage) closeButton.getScene().getWindow();
        boolean fullScreen = s.isFullScreen();
        if (fullScreen) {
            s.setFullScreen(false);
            topPane.setWidth(s.getWidth());
            logo.setScaleX(1);
            logo.setScaleY(1);
            logo.setLayoutY(-187);
            logo.setLayoutX(s.getWidth() / 2 - logo.getFitWidth() / 2);
            mainRectangle.setWidth(s.getWidth());
            mainRectangle.setHeight(s.getHeight());
        } else if (!(fullScreen)) {
            s.setFullScreen(true);
            topPane.setWidth(s.getWidth());
            mainRectangle.setWidth(s.getWidth());
            mainRectangle.setHeight(s.getHeight());
            logo.setScaleX(2);
            logo.setScaleY(2);
            logo.setLayoutY( s.getHeight()*0.05);
            logo.setLayoutX(s.getWidth() / 2 - logo.getFitWidth() / 2);

        }

    }

    @FXML
    protected void handleClickAction(MouseEvent event) {
        xOffset = event.getScreenX() - (topPane.getScene().getWindow()).getX();
        yOffset = event.getScreenY() - (topPane.getScene().getWindow()).getY();
    }

    @FXML
    protected void handleMovementAction(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

}