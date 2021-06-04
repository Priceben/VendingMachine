package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private String slotLocation;


    public VendingMachine(FileInteractor fileInteractor){
        this.actualInventory = fileInteractor.load();
    }


    //vendingmachine.csv
    public Map<String, ItemInventory> actualInventory = new HashMap<>();
 // Map<Integer, Item> actualInventory = new HashMap<>();
    Register register = new Register();

    //still have to make display
    public void displayInventory(){
        for(Map.Entry<String, ItemInventory> entry : actualInventory.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getItem().getName() + " " + entry.getValue().getItem().getType() + " " + entry.getValue().getItem().getName() + " " + entry.getValue().getInventoryCount());
        }
    }



}
