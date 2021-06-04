package com.techelevator;

public class Item extends AbstractItems{

    //Constructors

    public Item(String name, int price) {
        super(name, price);
    }

    //TODO delete this it's a dummy
    public Item(String name, String type) {
        super(name, type);
    }

    public Item(String name, String type, int price) {
        super(name, price, type);
    }

    //public Item (String slotLocation, String name, int price) {
       // super(slotLocation, name, price);
   // }

    public Item(String name, int price, String type) {
        super(name, price, type);
    }


}
