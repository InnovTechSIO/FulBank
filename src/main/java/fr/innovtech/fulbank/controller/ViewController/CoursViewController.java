package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.elements.CryptoCard;
import fr.innovtech.fulbank.elements.CryptoCardCell;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class CoursViewController extends ViewController implements Initializable {

    @FXML
    ListView<CryptoCard> coursListView;

    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        ArrayList<HashMap<String, Object>> cryptosData = App.cryptosData;
        List<CryptoCard> cryptoCards = new ArrayList<>();

        for (HashMap<String, Object> cryptoData : cryptosData) {
            String name = (String) cryptoData.get("name");
            double value = (Double) cryptoData.get("current_price");
            String imageUrl = (String) cryptoData.get("image");


            CryptoCard cryptoCard = new CryptoCard(name, value, imageUrl);
            cryptoCards.add(cryptoCard);
        }

        coursListView.setItems(FXCollections.observableList(cryptoCards));
        coursListView.setCellFactory(param -> new CryptoCardCell());
    }
}
