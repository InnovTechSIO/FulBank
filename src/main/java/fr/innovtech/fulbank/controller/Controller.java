package fr.innovtech.fulbank.controller;


import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.entities.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller{


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
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label label_error_connexion;

    @FXML
    private  TextField name;

    @FXML
    private TextField firstname;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField card_number;

    @FXML
    private TextField IBAN;

    @FXML
    private PasswordField password_register;

    @FXML
    private Label label_registration_failed;



    @FXML
    protected void handleClickAction(MouseEvent event) {

        xOffset = event.getScreenX() - (topPane.getScene().getWindow()).getX();
        yOffset = event.getScreenY() - (topPane.getScene().getWindow()).getY();

    }
    @FXML
    protected void switchSceneAccount(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
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

    protected void switchToaccount_creationView(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToRegisterView(MouseEvent event) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML

    protected void connexion_clicked(MouseEvent event){

        String username = this.username.getText();
        String password = this.password.getText();

        boolean isUser = CustomerDBController.checkUser(username, password);

        if(isUser){
            System.out.println("User is connected");
            try {
                switchSceneAccount(event);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else{
            System.out.println("User is not connected");
            label_error_connexion.setVisible(true);
        }

    }

    @FXML

    protected void register_clicked(MouseEvent event){
        String name = this.name.getText();
        String firstname = this.firstname.getText();
        String email = this.email.getText();
        String phone = this.phone.getText();
        String card_number = this.card_number.getText();
        String IBAN = this.IBAN.getText();
        String password = this.password_register.getText();

        
        boolean isRegistered = CustomerDBController.registerCustomer(name, firstname, email, phone, card_number, IBAN, password);

        if(isRegistered){
            System.out.println("User is registered");
            try {
                switchToConnexionView(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("User is not registered");
            label_registration_failed.setVisible(true);
        }
    }



    @FXML
    protected void handleMovementAction(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }



    @Override
    public void initialize(URL var1, ResourceBundle var2) {

    }
    
    @FXML
    protected void switchProfile(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}