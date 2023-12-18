package fr.innovtech.fulbank.elements;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class AccountCardCell extends ListCell<AccountCard> {

    private final HBox cardLayout;
    private final Label lbl_desc;

    public AccountCardCell(){
        cardLayout = new HBox(10);
        VBox textLayout = new VBox();
        setStyle("-fx-background-color: #80c2ec; -fx-border-color: #80c2ec; -fx-font-size: 90px");
        lbl_desc = new Label();
        lbl_desc.setStyle("-fx-font-size: 24px");


        textLayout.getChildren().addAll(lbl_desc);
        cardLayout.getChildren().addAll(textLayout);

        setGraphic(cardLayout);
    }

    @Override
    protected void updateItem(AccountCard item, boolean empty){
        super.updateItem(item,empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            lbl_desc.setText("Compte " + item.getName() + " :            " + item.getAmount() + " €");

            setStyle("-fx-background-color: " + (getIndex() % 2 == 0 ? "#80c2ec;" : "#45a7e6; "));
            setGraphic(cardLayout);

        }

    }
}
