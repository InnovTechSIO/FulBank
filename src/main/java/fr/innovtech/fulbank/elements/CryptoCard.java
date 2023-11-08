package fr.innovtech.fulbank.elements;


public class CryptoCard {
    private String name;
    private double value;
    private String imageUrl;
    private double highestValue;
    private double lowestValue;

    public CryptoCard(String name, double value, String imageUrl, double highestValue, double lowestValue) {
        this.name = name;
        this.value = value;
        this.imageUrl = imageUrl;
        this.highestValue = highestValue;
        this.lowestValue = lowestValue;

    }

    public double getHighestValue() {
        return highestValue;
    }

    public double getLowestValue() {
        return lowestValue;
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