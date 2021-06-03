package com.techelevator;

public class Item extends AbstractItems{

    //Constructors
    public Item (String name, int price) {
        super(name, price);
    }

    public Item (String name, int price, String slotLocation) {
        super(name, price, slotLocation);
    }

    public Item(String name, String type, int price, String slotLocation) {
        super(name, type, price, slotLocation);
    }


}
