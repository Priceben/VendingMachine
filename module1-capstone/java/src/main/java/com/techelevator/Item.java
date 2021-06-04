package com.techelevator;

public class Item extends AbstractItems{

    //Constructors
    public Item (String slotLocation) {
        super(slotLocation);
    }

    public Item(String name, int price) {
        super(name, price);
    }

    //TODO delete this it's a dummy
    public Item(String name, String type) {
        super(name, type);
    }

    public Item(String name, String type, int price) {
        super(name, type, price);
    }

    //public Item (String slotLocation, String name, int price) {
       // super(slotLocation, name, price);
   // }

    public Item(String slotLocation, String name, int price, String type) {
        super(slotLocation, name, price, type);
    }


}
