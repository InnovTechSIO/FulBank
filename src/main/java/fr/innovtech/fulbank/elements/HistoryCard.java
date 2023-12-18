package fr.innovtech.fulbank.elements;

import fr.innovtech.fulbank.entities.Account;

public class HistoryCard {

    private int idTransaction;

    private float amount;

    private String accountSubstract;

    private String accountAdd;

    private String customerNameSubstract;

    private String customerNameAdd;





    private String dateTransaction;

    private int idAccountSubstract;

    public HistoryCard(int idTransaction, float amount, String accountSubstract,String customerNameSubstract, String accountAdd,String customerNameAdd, String dateTransaction,int idAccountSubstract) {
        this.idTransaction = idTransaction;
        this.amount = amount;
        this.accountSubstract = accountSubstract;
        this.accountAdd = accountAdd;
        this.dateTransaction = dateTransaction;
        this.customerNameSubstract = customerNameSubstract;
        this.customerNameAdd = customerNameAdd;
        this.idAccountSubstract = idAccountSubstract;



    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public float getAmount() {
        return amount;
    }

    public String getAccountSubstract() {
        return accountSubstract;
    }


    public String getAccountAdd() {
        return accountAdd;
    }

    public int getIdAccountSubstract() {
        return idAccountSubstract;
    }



    public String getDateTransaction() {
        return dateTransaction;
    }

    public String getCustomerNameSubstract() {
        return customerNameSubstract;
    }

    public String getCustomerNameAdd() {
        return customerNameAdd;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setAccountSubstract(String accountSubstract) {
        this.accountSubstract = accountSubstract;
    }

    public void setAccountAdd(String accountAdd) {
        this.accountAdd = accountAdd;
    }

    public void setIdAccountSubstract(int idAccountSubstract) {
        this.idAccountSubstract = idAccountSubstract;
    }



    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public void setCustomerNameSubstract(String customerNameSubstract) {
        this.customerNameSubstract = customerNameSubstract;
    }

    public void setCustomerNameAdd(String customerNameAdd) {
        this.customerNameAdd = customerNameAdd;
    }
}
