package fr.innovtech.fulbank;

import fr.innovtech.fulbank.gateways.MySQLDBGateway;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1569, 970);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(true);
        stage.setTitle("FulBank");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("fulbank.png"))));
        stage.show();
        MySQLDBGateway.connection();

    }


    public static void main(String[] args) {
        launch();
    }

}