package fr.innovtech.fulbank.entities;


public class Customer {
    private String _firstName;
    private String _lastName;
    private String _mail;
    private String _phone;
    private String _password;

    public Customer(String _firstName, String _lastName, String _mail, String _phone, String _password) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._mail = _mail;
        this._phone = _phone;
        this._password = _password;
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public String get_mail() {
        return _mail;
    }

    public String get_password() {
        return _password;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public void set_mail(String _mail) {
        this._mail = _mail;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "_firstName='" + _firstName + '\'' +
                ", _lastName='" + _lastName + '\'' +
                ", _mail='" + _mail + '\'' +
                ", _phone='" + _phone + '\'' +
                ", _password='" + _password + '\'' +
                '}';
    }
}
