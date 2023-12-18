package fr.innovtech.fulbank.elements;

public class AccountCard {

    private String name;
    private float amount;
    private String type;

    public AccountCard(String aName, float anAmount, String aType){
        this.name = aName;
        this.amount = anAmount;
        this.type = aType;
    }

    public float getAmount(){ return this.amount; }

    public String getName(){ return this.name; }

    public String getType(){ return this.type; }
}
