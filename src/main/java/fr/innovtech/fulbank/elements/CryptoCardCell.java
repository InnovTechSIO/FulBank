package fr.innovtech.fulbank.elements;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CryptoCardCell extends ListCell<CryptoCard> {
    @Override
    protected void updateItem(CryptoCard item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            setStyle("-fx-background-color: #80e2ec; -fx-border-color: #80e2ec");
        } else {
            HBox cardLayout = new HBox();
            VBox textLayout = new VBox();
            ImageView imageView = new ImageView(item.getImageUrl());

            imageView.setFitHeight(50);
            imageView.setPreserveRatio(true);

            Label nameLabel = new Label(item.getName());
            Label valueLabel = new Label("Valeur: " + item.getValue() + " €");

            textLayout.getChildren().addAll(nameLabel, valueLabel);

            cardLayout.getChildren().addAll(imageView, textLayout);
            setStyle("-fx-background-color: #80e2ec; -fx-border-width: 1px; -fx-border-color: #818181");
            setGraphic(cardLayout);
        }
    }
}
