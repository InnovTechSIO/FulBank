package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.elements.CryptoCard;
import fr.innovtech.fulbank.elements.CryptoCardCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class CoursViewController extends ViewController implements Initializable {

    @FXML
    ListView<CryptoCard> coursListView;

    @FXML
    ImageView selectedImageView;

    @FXML
    Label selectedValueLabel;

    @FXML
    Label selectedNameLabel;

    @FXML
    Label selectedHighestValue;

    @FXML
    Label selectedLowestValue;

    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        ArrayList<HashMap<String, Object>> cryptosData = App.cryptosData;
        List<CryptoCard> cryptoCards = new ArrayList<>();

        for (HashMap<String, Object> cryptoData : cryptosData) {
            String name = (String) cryptoData.get("name");
            double value = (Double) cryptoData.get("current_price");
            String imageUrl = (String) cryptoData.get("image");
            double highestValue = (Double) cryptoData.get("high_24h");
            double lowestValue = (Double) cryptoData.get("low_24h");

            CryptoCard cryptoCard = new CryptoCard(name, value, imageUrl, highestValue, lowestValue);
            cryptoCards.add(cryptoCard);
        }

        coursListView.setItems(FXCollections.observableList(cryptoCards));
        coursListView.setCellFactory(param -> new CryptoCardCell());
        coursListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedImageView.setImage(new Image(newValue.getImageUrl()));
            selectedValueLabel.setVisible(true);
            selectedValueLabel.setText(String.format("Valeur: %s €", newValue.getValue()));
            selectedNameLabel.setVisible(true);
            selectedNameLabel.setText(String.format("Nom: %s", newValue.getName()));
            selectedHighestValue.setVisible(true);
            selectedHighestValue.setText(String.format("Plus haute valeur dernière 24h : %s €", newValue.getHighestValue()));
            selectedLowestValue.setVisible(true);
            selectedLowestValue.setText(String.format("Plus basse valeur dernière 24h : %s €", newValue.getLowestValue()));
        });

    }
    private static class LoadImageTask extends Task<Image> {
        private final String imageUrl;

        public LoadImageTask(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        protected Image call() throws Exception {
            return new Image(imageUrl, true);
        }
    }
}
