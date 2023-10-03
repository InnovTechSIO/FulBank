package fr.innovtech.fulbank.entities;

public class Beneficiary {

    private int _idBeneficiary;
    private String _name;
    private String _bank;
    private Customer _customer;

    public Beneficiary(int _idBeneficiary, String _name, String _bank, Customer _customer) {
        this._idBeneficiary = _idBeneficiary;
        this._name = _name;
        this._bank = _bank;
        this._customer = _customer;
    }

    public int get_idBeneficiary() {
        return _idBeneficiary;
    }

    public String get_name() {
        return _name;
    }

    public String get_bank() {
        return _bank;
    }

    public Customer get_customer() {
        return _customer;
    }

    public void set_idBeneficiary(int _idBeneficiary) {
        this._idBeneficiary = _idBeneficiary;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_bank(String _bank) {
        this._bank = _bank;
    }

    public void set_customer(Customer _customer) {
        this._customer = _customer;
    }

    @Override
    public String toString() {
            return "Beneficiary{" +
                    "_idBeneficiary=" + _idBeneficiary +
                    ", _name='" + _name + '\'' +
                    ", _bank='" + _bank + '\'' +
                    ", _customer=" + _customer +
                    '}';
        }
    }
