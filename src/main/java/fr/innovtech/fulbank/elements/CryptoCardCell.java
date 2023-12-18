package fr.innovtech.fulbank.elements;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class CryptoCardCell extends ListCell<CryptoCard> {
    private final HBox cardLayout;
    private final ImageView imageView;
    private final Label nameLabel;
    private final Label valueLabel;

    public CryptoCardCell() {
        cardLayout = new HBox(10);
        VBox textLayout = new VBox();
        imageView = new ImageView();
        nameLabel = new Label();
        valueLabel = new Label();
        setStyle("-fx-background-color: #224399; -fx-border-color: #224399");
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-margin: 10px");

        textLayout.getChildren().addAll(nameLabel, valueLabel);
        cardLayout.getChildren().addAll(imageView, textLayout);

        setGraphic(cardLayout);
    }

    @Override
    protected void updateItem(CryptoCard item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            nameLabel.setText(item.getName());
            valueLabel.setText("Valeur: " + item.getValue() + " €");

            LoadImageTask loadImageTask = new LoadImageTask(item.getImageUrl());
            imageView.imageProperty().bind(loadImageTask.valueProperty());
            new Thread(loadImageTask).start();

            setStyle("-fx-background-color: " + (getIndex() % 2 == 0 ? "#80e2ec;" : "#6ac3d2; -fx-border-color: #272727; -fx-border-width: 1px;"));
            setGraphic(cardLayout);
        }
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

