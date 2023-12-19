package fr.innovtech.fulbank.controller.ViewController;

import fr.innovtech.fulbank.App;
import fr.innovtech.fulbank.controller.DBController.AccountDBController;
import fr.innovtech.fulbank.controller.DBController.CategorieDBController;
import fr.innovtech.fulbank.controller.DBController.CustomerDBController;
import fr.innovtech.fulbank.controller.DBController.HistoryDBController;

import fr.innovtech.fulbank.elements.HistoryCard;
import fr.innovtech.fulbank.elements.HistoryCardCell;
import fr.innovtech.fulbank.entities.Account;
import fr.innovtech.fulbank.entities.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryViewController extends ViewController implements Initializable {

    @FXML
    ListView<HistoryCard> historyListView;

    @FXML
    ComboBox<String> cbx_choiceAccount;

    @FXML
    Button btn_TestHistory;





    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        historyListView.setStyle("-fx-background-color: #80e2ec;");
        cbx_choiceAccount.getItems().clear();
        int idCustomer = CustomerDBController.connectedCustomer.get_id();
        cbx_choiceAccount.getItems().add("all");
        cbx_choiceAccount.getItems().addAll(CategorieDBController.getCategoryByCustomer(idCustomer));
        cbx_choiceAccount.getSelectionModel().selectFirst();


        List<Integer> allIdAccount = HistoryDBController.getAllIdAccountsByCustomer(idCustomer);
        List<Transaction> allTransaction = new ArrayList<>();
        List<Transaction> allTransactionSubstract = new ArrayList<>();
        List<Transaction> allTransactionadd = new ArrayList<>();

        for(Integer idAccount : allIdAccount){
            allTransactionadd.addAll(HistoryDBController.getAllTransactionsByAccountAdd(idAccount));
            allTransactionSubstract.addAll(HistoryDBController.getAllTransactionsByAccountSubstract(idAccount));
        }

        allTransaction.addAll(allTransactionadd);
        allTransaction.addAll(allTransactionSubstract);

        setListView(historyListView, allTransactionadd, allTransactionSubstract);


        cbx_choiceAccount.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

            historyListView.getItems().clear();
            historyListView.refresh();

            allTransaction.clear();
            allTransactionSubstract.clear();
            allTransactionadd.clear();
            if(newValue.equals("all")){
                for(Integer idAccount : allIdAccount){
                    allTransactionadd.addAll(HistoryDBController.getAllTransactionsByAccountAdd(idAccount));
                    allTransactionSubstract.addAll(HistoryDBController.getAllTransactionsByAccountSubstract(idAccount));
                }

                allTransaction.addAll(allTransactionadd);
                allTransaction.addAll(allTransactionSubstract);

                setListView(historyListView, allTransactionadd, allTransactionSubstract);

            }
            else{
                int idAccount = AccountDBController.getIdAccountByCategoryName(newValue);

                allTransactionadd.addAll(HistoryDBController.getAllTransactionsByAccountAddByCategorie(idAccount, newValue));
                allTransactionSubstract.addAll(HistoryDBController.getAllTransactionsByAccountSubstractByCategorie(idAccount, newValue));

                System.out.println("separation");



                if(allTransactionSubstract.isEmpty()){
                    changeListView(historyListView, allTransactionadd, allTransactionSubstract,false);

                }
                else{
                    changeListView(historyListView, allTransactionadd, allTransactionSubstract,true);
                }


            }






        });



    }


    private void setListView(ListView<HistoryCard> historyListView, List<Transaction> allTransactionadd, List<Transaction> allTransactionSubstract) {


        List<HistoryCard> historyCards = new ArrayList<>();



        for(Transaction transaction : allTransactionadd) {

            int idTransaction = transaction.get_idtransaction();
            float amount = transaction.get_amount();
            Account accountsubstract = transaction.get_accountsubstract();
            Account accountadd = transaction.get_accountadd();
            String dateTransaction = transaction.get_datetransaction().toString();
            String customerNameSubstract = accountsubstract.get_customer().get_lastName();
            String customerNameAdd = accountadd.get_customer().get_lastName();
            int idAccountSubstract = accountsubstract.get_number();




            HistoryCard historyCard = new HistoryCard(idTransaction, amount, accountsubstract.get_category().get_wording(),customerNameSubstract, accountadd.get_category().get_wording(),customerNameAdd, dateTransaction, idAccountSubstract, false);
            historyCards.add(historyCard);

        }


        for(Transaction transaction : allTransactionSubstract) {

            int idTransaction = transaction.get_idtransaction();
            float amount = transaction.get_amount();
            Account accountsubstract = transaction.get_accountsubstract();
            Account accountadd = transaction.get_accountadd();
            String dateTransaction = transaction.get_datetransaction().toString();
            String customerNameSubstract = accountsubstract.get_customer().get_lastName();
            String customerNameAdd = accountadd.get_customer().get_lastName();
            int idAccountSubstract = accountsubstract.get_number();




            HistoryCard historyCard = new HistoryCard(idTransaction, amount, accountsubstract.get_category().get_wording(),customerNameSubstract, accountadd.get_category().get_wording(),customerNameAdd, dateTransaction, idAccountSubstract, true);
            historyCards.add(historyCard);

        }

        historyListView.setItems(FXCollections.observableList(historyCards));
        historyListView.setCellFactory(param -> new HistoryCardCell());



    }

    private void changeListView(ListView<HistoryCard> historyListView, List<Transaction> allTransactionadd, List<Transaction> allTransactionSubstract, boolean isSubstract) {


        List<HistoryCard> historyCards = new ArrayList<>();


        if(isSubstract){
            for(Transaction transaction : allTransactionSubstract) {

                int idTransaction = transaction.get_idtransaction();
                float amount = transaction.get_amount();
                Account accountsubstract = transaction.get_accountsubstract();
                Account accountadd = transaction.get_accountadd();
                String dateTransaction = transaction.get_datetransaction().toString();
                String customerNameSubstract = accountsubstract.get_customer().get_lastName();
                String customerNameAdd = accountadd.get_customer().get_lastName();
                int idAccountSubstract = accountsubstract.get_number();




                HistoryCard historyCard = new HistoryCard(idTransaction, amount, accountsubstract.get_category().get_wording(),customerNameSubstract, accountadd.get_category().get_wording(),customerNameAdd, dateTransaction, idAccountSubstract, true);
                historyCards.add(historyCard);


            }



        }
        else {

            for(Transaction transaction : allTransactionadd) {

                int idTransaction = transaction.get_idtransaction();
                float amount = transaction.get_amount();
                Account accountsubstract = transaction.get_accountsubstract();
                Account accountadd = transaction.get_accountadd();
                String dateTransaction = transaction.get_datetransaction().toString();
                String customerNameSubstract = accountsubstract.get_customer().get_lastName();
                String customerNameAdd = accountadd.get_customer().get_lastName();
                int idAccountSubstract = accountsubstract.get_number();




                HistoryCard historyCard = new HistoryCard(idTransaction, amount, accountsubstract.get_category().get_wording(),customerNameSubstract, accountadd.get_category().get_wording(),customerNameAdd, dateTransaction, idAccountSubstract, false);
                historyCards.add(historyCard);


            }

        }
        historyListView.setItems(FXCollections.observableList(historyCards));
        historyListView.setCellFactory(param -> new HistoryCardCell());














    }
}
