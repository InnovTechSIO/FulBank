package fr.innovtech.fulbank.entities;

public class DAB {

    private int _iddab;
    private String _attitude;
    private String _longitude;

    public DAB(int _iddab, String _attitude, String _longitude) {
        this._iddab = _iddab;
        this._attitude = _attitude;
        this._longitude = _longitude;
    }

    public int get_iddab() {
        return _iddab;
    }

    public String get_attitude() {
        return _attitude;
    }

    public String get_longitude() {
        return _longitude;
    }

    public void set_iddab(int _iddab) {
        this._iddab = _iddab;
    }

    public void set_attitude(String _attitude) {
        this._attitude = _attitude;
    }

    public void set_longitude(String _longitude) {
        this._longitude = _longitude;
    }

    @Override
    public String toString() {
        return "DAB{" +
                "_iddab=" + _iddab +
                ", _attitude='" + _attitude + '\'' +
                ", _longitude='" + _longitude + '\'' +
                '}';
    }

}
