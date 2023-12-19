package fr.innovtech.fulbank.elements;
import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HistoryCardCell extends ListCell<HistoryCard> {

    private  final HBox cardLayout;

    private final Label amountLabel;

    private final Label accountSubstractLabel;

    private final Label accountAddLabel;

    private final Label dateTransactionLabel;



    public HistoryCardCell() {
        cardLayout = new HBox(10);
        VBox textLayout = new VBox();
        amountLabel = new Label();
        accountSubstractLabel = new Label();
        accountAddLabel = new Label();
        dateTransactionLabel = new Label();

        setStyle("-fx-background-color: #80e2ec; -fx-border-color: #80e2ec;");
        textLayout.getChildren().addAll(amountLabel, accountSubstractLabel,accountAddLabel, dateTransactionLabel);
        cardLayout.getChildren().addAll(amountLabel, textLayout);

        setGraphic(cardLayout);
    }


    @Override
    protected void updateItem(HistoryCard item, boolean empty) {
        super.updateItem(item, empty);
        int idCustomer = CustomerDBController.connectedCustomer.get_id();



        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            setStyle("-fx-background-color: #80e2ec;");



        } else {
            if(item.isSubstract()){
                amountLabel.setText("-" + item.getAmount());
                    amountLabel.setStyle("-fx-text-fill: red");

            }
            else{
                amountLabel.setText("+" + item.getAmount());
                amountLabel.setStyle("-fx-text-fill: green");
            }

            accountSubstractLabel.setText("De : " + item.getCustomerNameSubstract() + "avec son compte : " + item.getAccountSubstract());
            accountAddLabel.setText("Vers : " + item.getCustomerNameAdd() + "sur le compte : " + item.getAccountAdd());
            dateTransactionLabel.setText(item.getDateTransaction());


            setStyle("-fx-background-color: " + (getIndex() % 2 == 0 ? "#80e2ec;" : "#6ac3d2; -fx-border-color: #272727; -fx-border-width: 1px;"));
            setGraphic(cardLayout);
            System.out.println(item.getAmount());
        }
    }
}
