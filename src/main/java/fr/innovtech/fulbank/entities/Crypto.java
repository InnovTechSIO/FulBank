package fr.innovtech.fulbank.entities;

public class Crypto {

    private int number;
    private String wording;
    private String libelle;

    public Crypto(int number, String wording, String libelle) {
        this.number = number;
        this.wording = wording;
        this.libelle = libelle;
    }

    public int getNumber() {
        return number;
    }

    public String getWording() {
        return wording;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }



}
