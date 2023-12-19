package fr.innovtech.fulbank.entities;


import java.util.Date;

public class Transaction {

    private int _idtransaction;
    private Date _datetransaction;
    private Float _amount;

    private Account _accountsubstract;
    private Account _accountadd;

    private Transactiontype _transactiontype;

    private  DAB _dab;

    public Transaction(int _idtransaction, Date _datetransaction, Float _amount, Account _accountsubstract, Account _accountadd, Transactiontype _transactiontype, DAB _dab) {
        this._idtransaction = _idtransaction;
        this._datetransaction = _datetransaction;
        this._amount = _amount;
        this._accountsubstract = _accountsubstract;
        this._accountadd = _accountadd;
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

    public Account get_accountsubstract() {
        return _accountsubstract;
    }

    public Account get_accountadd() {
        return _accountadd;
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

    public void set_accountsubstract(Account _accountsubstract) {
        this._accountsubstract = _accountsubstract;
    }

    public void set_accountadd(Account _accountadd) {
        this._accountadd = _accountadd;
    }

    public void set_transactiontype(Transactiontype _transactiontype) {
        this._transactiontype = _transactiontype;
    }

    public void set_dab(DAB _dab) {
        this._dab = _dab;
    }


    public void transactionmoney(Float amount, Account sender,Account receiver, Transactiontype transactiontype, DAB dab) {
        if (transactiontype.get_number() == 1) {
            sender.set_amount(sender.get_amount() - amount);
            receiver.set_amount(receiver.get_amount() + amount);
        }
        else return;


    }

    @Override
    public String toString() {
        return "Transaction{" +
                "_idtransaction=" + _idtransaction +
                ", _datetransaction=" + _datetransaction +
                ", _amount=" + _amount +
                ", _accountsubstract=" + _accountsubstract +
                ", _accountadd=" + _accountadd +
                ", _transactiontype=" + _transactiontype +
                ", _dab=" + _dab +
                '}';
    }


}
