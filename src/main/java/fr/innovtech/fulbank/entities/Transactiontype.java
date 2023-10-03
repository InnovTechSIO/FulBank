package fr.innovtech.fulbank.entities;

public class Transactiontype {

    private int _number;
    private String _wording;

    public Transactiontype(int _number, String _wording) {
        this._number = _number;
        this._wording = _wording;
    }

    public int get_number() {
        return _number;
    }

    public String get_wording() {
        return _wording;
    }

    public void set_number(int _number) {
        this._number = _number;
    }

    public void set_wording(String _wording) {
        this._wording = _wording;
    }

    @Override
    public String toString() {
        return "Transactiontype{" +
                "_number=" + _number +
                ", _wording='" + _wording + '\'' +
                '}';
    }
}
