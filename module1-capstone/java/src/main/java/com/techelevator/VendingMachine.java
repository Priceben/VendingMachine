package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachine extends Register {
    //Instance Variables
    public Map<String, ItemInventory> actualInventory = new HashMap<>();

    //Constructors
    public VendingMachine(FileInteractor fileInteractor){
        this.actualInventory = fileInteractor.load();
    }

    //Methods
    public void displayInventory(){
        Map<String, ItemInventory> orderedInventory = new TreeMap<String, ItemInventory>(actualInventory);
        for (Map.Entry<String, ItemInventory> entry : orderedInventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getItem().getName() + ", $" + entry.getValue().getItem().getPrice() + ", " + entry.getValue().getItem().getType() + ", " + entry.getValue().getInventoryCount());
        }
    }

    @Override
    public void purchase(Item item) {
        int registerBalance = getBalance();
        if(registerBalance >= item.getPrice()) {
            purchasedItems.add(item);
            registerBalance = getBalance() - item.getPrice();
        } else {
            registerBalance = registerBalance;
        }
    }

    //TODO: Make a method for vm to dispense based on user selection




}
