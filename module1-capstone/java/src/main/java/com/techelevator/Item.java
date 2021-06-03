package com.techelevator;

public class Item extends AbstractItems{

    //Constructors
    public Item (String slotLocation) {
        super(slotLocation);
    }

    public Item(String name, int price) {
        super(name, price);
    }

    public Item (String slotLocation, String name, int price) {
        super(slotLocation, name, price);
    }

    public Item(String slotLocation, String name, int price, String type) {
        super(slotLocation, name, price, type);
    }


}
