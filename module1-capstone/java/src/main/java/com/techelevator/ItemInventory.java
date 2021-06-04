package com.techelevator;


public class ItemInventory {
    private int inventoryCount = 5;
    private Item item;

    //getters

    public int getInventoryCount() {
        return inventoryCount;
    }

    public Item getItem() {
        return item;
    }
    //setters

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    //Constructor
    public ItemInventory(int inventoryCount, Item item){
        this.inventoryCount = inventoryCount;
        this.item = item;
    }


}
