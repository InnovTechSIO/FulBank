package fr.innovtech.fulbank.elements;

public class CryptoCard {
    private String name;
    private double value;
    private String imageUrl;

    public CryptoCard(String name, double value, String imageUrl) {
        this.name = name;
        this.value = value;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}