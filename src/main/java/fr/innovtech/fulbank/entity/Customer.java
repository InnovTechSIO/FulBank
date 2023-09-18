package fr.innovtech.fulbank.entity;

public class Customer {
    private String _firstName;
    private String _lastName;
    private String _mail;
    private String _phoneNumber;
    private String _password;


    public Customer(String firstName, String lastName, String mail, String phoneNumber, String password) {
        this._firstName = firstName;
        this._lastName = lastName;
        this._mail = mail;
        this._phoneNumber = phoneNumber;
        this._password = password;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    public String getMail() {
        return _mail;
    }

    public void setMail(String mail) {
        this._mail = mail;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this._phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "_firstName='" + _firstName + '\'' +
                ", _lastName='" + _lastName + '\'' +
                ", _mail='" + _mail + '\'' +
                ", _phoneNumber='" + _phoneNumber + '\'' +
                ", _password='" + _password + '\'' +
                '}';
    }
}
