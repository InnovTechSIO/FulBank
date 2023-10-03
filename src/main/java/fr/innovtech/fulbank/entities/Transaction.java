package fr.innovtech.fulbank.entities;

import fr.innovtech.fulbank.annotations.NotNull;

import java.util.Date;

public class Transaction {

    private int _idtransaction;
    private Date _datetransaction;
    private Float _amount;

    private Account _account;

    private Transactiontype _transactiontype;

    private  DAB _dab;

    public Transaction(int _idtransaction, Date _datetransaction, Float _amount, Account _account, Transactiontype _transactiontype, DAB _dab) {
        this._idtransaction = _idtransaction;
        this._datetransaction = _datetransaction;
        this._amount = _amount;
        this._account = _account;
        this._transactiontype = _transactiontype;
        this._dab = _dab;
    }

    public int get_idtransaction() {
        return _idtransaction;
    }

    public Date get_datetransaction() {
        return _datetransaction;
    }

    public Float get_amount() {
        return _amount;
    }

    public Account get_account() {
        return _account;
    }

    public Transactiontype get_transactiontype() {
        return _transactiontype;
    }

    public DAB get_dab() {
        return _dab;
    }

    public void set_idtransaction(int _idtransaction) {
        this._idtransaction = _idtransaction;
    }

    public void set_datetransaction(Date _datetransaction) {
        this._datetransaction = _datetransaction;
    }

    public void set_amount(Float _amount) {
        this._amount = _amount;
    }

    public void set_account(Account _account) {
        this._account = _account;
    }

    public void set_transactiontype(Transactiontype _transactiontype) {
        this._transactiontype = _transactiontype;
    }

    public void set_dab(DAB _dab) {
        this._dab = _dab;
    }


  

    @Override
    public String toString() {
        return "Transaction{" +
                "_idtransaction=" + _idtransaction +
                ", _datetransaction=" + _datetransaction +
                ", _amount=" + _amount +
                ", _account=" + _account +
                ", _transactiontype=" + _transactiontype +
                ", _dab=" + _dab +
                '}';
    }


}
