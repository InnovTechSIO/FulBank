package fr.innovtech.fulbank.entities;

import java.util.Date;

public class Account {

    private int _number;
    private Float _amount;
    private Date _creationDate;
    private Date _updateDate;
    private Date deletionDate;
    private Customer _customer;
    private Category _category;

    public Account(int _number, Float _amount, Date _creationDate, Date _updateDate, Date deletionDate, Customer _customer, Category _category) {
        this._number = _number;
        this._amount = _amount;
        this._creationDate = _creationDate;
        this._updateDate = _updateDate;
        this.deletionDate = deletionDate;
        this._customer = _customer;
        this._category = _category;
    }

    public int get_number() {
        return _number;
    }

    public Float get_amount() {
        return _amount;
    }

    public Date get_creationDate() {
        return _creationDate;
    }

    public Date get_updateDate() {
        return _updateDate;
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    public Customer get_customer() {
        return _customer;
    }

    public Category get_category() {
        return _category;
    }

    public void set_number(int _number) {
        this._number = _number;
    }

    public void set_amount(Float _amount) {
        this._amount = _amount;
    }



    public void set_updateDate(Date _updateDate) {
        this._updateDate = _updateDate;
    }

    public void setDeletionDate(Date deletionDate) {
        this.deletionDate = deletionDate;
    }

    public void set_customer(Customer _customer) {
        this._customer = _customer;
    }

    public void set_category(Category _category) {
        this._category = _category;
    }

    public void set_creationDate(Date _creationDate) {
        this._creationDate = _creationDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "_number=" + _number +
                ", _amount=" + _amount +
                ", _creationDate=" + _creationDate +
                ", _updateDate=" + _updateDate +
                ", deletionDate=" + deletionDate +
                ", _customer=" + _customer +
                ", _category=" + _category +
                '}';
    }
}
