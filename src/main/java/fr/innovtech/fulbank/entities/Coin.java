package fr.innovtech.fulbank.entities;


public class Coin {
    private String id;
    private String symbol;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    @Override
    public String toString() {
        return "Coin{" +
                "id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
