package com.techelevator;

public abstract class AbstractItems {
    private String name;
    private String type;
    private int price;


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



    //Constructors

    public AbstractItems(String name, int price) {
        this.name = name;
        this.price = price;
    }

    //TODO DELETE THIS IT'S A DUMMY
    public AbstractItems(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public AbstractItems(String name,  int price, String type) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

   /* public AbstractItems(String slotLocation, String name, int price) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
    }*/


    //Methods
    //TODO: Update Inventory Method? Not sure
    //TODO: Do we need dispensable? We think no, but just in case...


}
