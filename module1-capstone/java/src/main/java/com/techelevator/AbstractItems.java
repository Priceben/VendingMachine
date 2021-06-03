package com.techelevator;

public abstract class AbstractItems {
    private String name;
    private String type;
    private int price;
    private String slotLocation;

    //Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    //Constructors
    public AbstractItems(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public AbstractItems(String name, int price, String slotLocation) {
        this.name = name;
        this.price = price;
        this.slotLocation = slotLocation;
    }

    public AbstractItems(String name, String type, int price, String slotLocation) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.slotLocation = slotLocation;
    }

    //Methods
    //TODO: Update Inventory Method? Not sure
    //TODO: Do we need dispensable? We think no, but just in case...


}
